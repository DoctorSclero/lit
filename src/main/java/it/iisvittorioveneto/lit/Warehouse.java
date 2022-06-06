package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.database.JSONArrayDocument;
import it.iisvittorioveneto.lit.database.JSONDocument;
import it.iisvittorioveneto.lit.database.JSONObjectDocument;
import it.iisvittorioveneto.lit.exceptions.DirectoryCreationException;
import it.iisvittorioveneto.lit.model.User;
import it.iisvittorioveneto.lit.model.Version;
import it.iisvittorioveneto.lit.database.utils.JSONArray;
import it.iisvittorioveneto.lit.database.utils.JSONObject;
import it.iisvittorioveneto.lit.utils.Zipper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Warehouse {

    // JSON Database's Documents
    private JSONDocument<JSONObject> settingsDB;
    private JSONDocument<JSONObject> statsDB;
    private JSONDocument<JSONArray> versionsDB;

    /**
     * Creating a new warehouse instance from an existing one
     * @param path The path of the existing warehouse
     */
    public Warehouse(String path) {
        // Check if the path exists
        if (Paths.get(path).toFile().exists()) {
            // Check if the path has a ".lit" directory inside
            if (Paths.get(path + "/.lit").toFile().exists()) {
                try {

                    // Read the settings file
                    settingsDB = new JSONObjectDocument(Paths.get(path, "/.lit/database/config.json"));
                    statsDB = new JSONObjectDocument(Paths.get(path, "/.lit/database/stats.json"));
                    versionsDB = new JSONArrayDocument(Paths.get(path, "/.lit/database/versions.json"));

                } catch (IOException ioe) {
                    // Log the error
                    System.out.println("Error while opening the config file");
                }
            } else {
                throw new InvalidPathException(path, "The path does not contain a .lit directory");
            }
        }
    }

    /*
     ! POSSIBLE BUG:
     ! When a new directory is created, if the .lit directory fails
     ! to be created, the new folder is not deleted
     */

    /**
     * Creating a new warehouse instance from scratch
     * @param name The name of the new warehouse
     * @param path The path of the new warehouse
     * @param description The description of the new warehouse
     * @param owner The owner of the new warehouse
     * @throws DirectoryCreationException If the .lit directory cannot be created
     * @throws InvalidPathException If the .lit directory already exists
     */
    public Warehouse(String name, String path, String description, User owner) throws InvalidPathException, DirectoryCreationException {

        // If path does not exist, create it
        Paths.get(path).toFile().mkdir(); // ! Possible bug location

        // Check if the path has a ".lit" directory inside
        if (Paths.get(path + "/.lit").toFile().exists()) {
            throw new InvalidPathException(path, "The path already contains a .lit directory");
        }

        // Creating the .lit folder structure if error occurs delete the whole directory
        if (
                !(
                    Paths.get(path, "/.lit").toFile().mkdir() &&
                    Paths.get(path, "/.lit/versions").toFile().mkdir() &&
                    Paths.get(path, "/.lit/database").toFile().mkdir()
                )
        ) {
            // Delete the directory
            Paths.get(path + "/.lit").toFile().delete();
            // Throw an exception
            throw new DirectoryCreationException(path, "Error while creating the .lit directory");
        }

        try {
            // Creating the settings, stats and versions files inside the .lit directory and loading them
            settingsDB = new JSONObjectDocument(Paths.get(path, "/.lit/database/config.json"));
            statsDB = new JSONObjectDocument(Paths.get(path, "/.lit/database/stats.json"));
            versionsDB = new JSONArrayDocument(Paths.get(path, "/.lit/database/versions.json"));

            // Setting the name, description and owner of the warehouse
            this.setName(name);
            this.setPath(path);
            this.setDescription(description);
            this.setOwner(owner);

        } catch (IOException ioe) {
            // Log the error
            System.out.println("Error while creating the config file");
            // Delete the directory
            Paths.get(path, "/.lit").toFile().delete();
        }
    }

    /**
     * Saves a new snapshot of the warehouse and stores it
     * @param name The name of the snapshot
     * @param contributors The contributors of the snapshot
     */
    public void saveVersion(String name, List<User> contributors) {

        // If the version directory cannot be created throw an exception
        if (!Paths.get(this.getPath()).toFile().exists()) { return; }

        // Log
        System.out.println("Saving version...");

        // Selecting all files and directories except the .lit folder
        FileFilter fileFilter = (file) -> !file.getName().equals(".lit");
        File[] versionContent = Paths.get(this.getPath()).toFile().listFiles(fileFilter);

        Version version = new Version(name, contributors);

        // Storing the version in the database
        versionsDB.getContent().put(version.toJSONObject());
        versionsDB.save();

        // Updating the statistics
        statsDB.getContent().put("last_version", version.getId());

        JSONArray storedContributors;
        if (statsDB.getContent().has("contributors"))
            storedContributors = statsDB.getContent().getJSONArray("contributors");
        else
            storedContributors = new JSONArray();

        for (User contributor : contributors) {
            // If there are stored contributors
            if (storedContributors.length() > 0) {
                boolean found = false;
                // Check if the contributor is already in the list
                for (int i = 0; i < storedContributors.length() && !found; i++) {
                    if (storedContributors.getJSONObject(i).get("email").equals(contributor.getEmail())) {
                        found = true;
                    }
                }

                if (!found) storedContributors.put(contributor.toJSONObject());
            } else {
                storedContributors.put(contributor.toJSONObject());
            }
        }

        statsDB.getContent().put("contributors", storedContributors);
        statsDB.save();

        // Creating the .lit/versions/{versionID} folder
        File versionDir = Paths.get(this.getPath(), "/.lit/versions/", version.getId()).toFile();

        if (!versionDir.mkdir()) {
            System.out.println("Error while creating version folder");
            return;
        }

        if (versionContent != null) {
            for (File file : versionContent) {
                try {
                    System.out.println("Copying file " + file.getName() + "...");
                    Files.copy(
                            file.toPath(),
                            Paths.get(versionDir.getPath(), "/", file.getName()),
                            REPLACE_EXISTING
                    );
                } catch (IOException e) {
                    System.out.println("Error while copying file: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Returns the list of all versions of the warehouse
     * @param filePath The path of the file to be loaded
     * @throws IOException If the file cannot be loaded
     */
    public void importFile(String filePath) throws IOException {

        File src = new File(filePath);
        File dest = new File(this.getPath());

        Files.copy(src.toPath(), dest.toPath());

    }

    /**
     * Restores a snapshot of the warehouse
     * @param versionID The ID of the version to restore
     * @param user The user who is requesting the version restoration
     */
    public void restoreVersion(String versionID, User user) throws IOException {
        // if there is no version that matches the provided versionID, throw an exception
        for (int i = 0; i < versionsDB.getContent().length(); i++) {
            if (versionsDB.getContent().getJSONObject(i).get("id").equals(versionID)) {
                // Set the user who is restoring the version as contributor of the version
                LinkedList<User> contributors = new LinkedList<>();
                contributors.add(user);
                // Save the current files in a new version
                saveVersion(
                        "Reverting the " + versionID + " version",
                        contributors
                );

                // Delete the current files
                FileFilter fileFilter = (file) -> !file.getName().equals(".lit");
                File[] warehouseContent = Paths.get(this.getPath()).toFile().listFiles(fileFilter);

                if (warehouseContent != null) {
                    for (File file : warehouseContent) {
                        if (file.isDirectory()) {
                            FileUtils.deleteDirectory(file);
                        } else {
                            file.delete();
                        }
                    }
                }

                // Restore the version
                File versionDir = Paths.get(this.getPath(), "/.lit/versions/", versionID).toFile();
                File[] versionContent = versionDir.listFiles();

                // If there are file in the version to restore
                if (versionContent != null) {
                    // Copy each file to the warehouse directory
                    for (File file : versionContent) {
                        try {
                            System.out.println("Copying file " + file.getName() + "...");
                            Files.copy(
                                    file.toPath(),
                                    Paths.get(this.getPath(), "/", file.getName()),
                                    REPLACE_EXISTING
                            );
                        } catch (IOException e) {
                            System.out.println("Error while copying file: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    public void getCollaborators() {

    }

    public void getVersions() {

    }

    public void getFileStats() {

    }

    /**
     * Saves all the databases.
     */
    public void save(){
        settingsDB.save();
        statsDB.save();
        versionsDB.save();
    }

    /**
     * Export the warehouse to a zip file
     * @param exportPath The path of the zip file
     * @throws IOException If the zip file cannot be created
     */
    public void exportWarehouse(String exportPath) throws IOException {
        Zipper.zipDirectory(this.getPath(), exportPath);
    }

    /**
     * Get the name of the warehouse
     * @return The name of the warehouse
     */
    public String getName() {
        return settingsDB.getContent().getString("name");
    }

    /**
     * Set the name of the warehouse
     * @param name The new name of the warehouse
     */
    public void setName(String name) {
        settingsDB.getContent().put("name", name);
        settingsDB.save();
    }

    /**
     * Get the path of the warehouse
     * @return The path of the warehouse
     */
    public String getPath() {
        return settingsDB.getContent().getString("path");
    }

    /**
     * Set the path of the warehouse
     * @param path The new path of the warehouse
     */
    public void setPath(String path) {
        settingsDB.getContent().put("path", path);
        settingsDB.save();
    }

    /**
     * Get the description of the warehouse
     * @return The description of the warehouse
     */
    public String getDescription() {
        return settingsDB.getContent().getString("description");
    }

    /**
     * Set the description of the warehouse
     * @param description The new description of the warehouse
     */
    public void setDescription(String description) {
        settingsDB.getContent().put("description", description);
        settingsDB.save();
    }

    /**
     * Get the owner of the warehouse
     * @return The owner of the warehouse
     */
    public User getOwner() {
        return new User(settingsDB.getContent().getJSONObject("user"));
    }

    /**
     * Set the owner of the warehouse
     * @param owner The new owner of the warehouse
     */
    public void setOwner(User owner) {
        settingsDB.getContent().put("user", owner.toJSONObject());
        settingsDB.save();
    }
}
