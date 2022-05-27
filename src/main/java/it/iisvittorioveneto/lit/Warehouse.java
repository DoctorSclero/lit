package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.database.JSONDocument;
import it.iisvittorioveneto.lit.database.JSONType;
import it.iisvittorioveneto.lit.exceptions.DirectoryCreationException;
import it.iisvittorioveneto.lit.model.User;
import it.iisvittorioveneto.lit.model.Version;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
                    settingsDB = new JSONDocument<>(path + "/.lit/database/config.json");
                    statsDB = new JSONDocument<>(path + "/.lit/database/stats.json");
                    versionsDB = new JSONDocument<>(path + "/.lit/database/versions.json");

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
                    Paths.get(path + "/.lit").toFile().mkdir() &&
                    Paths.get(path + "/.lit/versions").toFile().mkdir() &&
                    Paths.get(path + "/.lit/database").toFile().mkdir()
                )
        ) {
            // Delete the directory
            Paths.get(path + "/.lit").toFile().delete();
            // Throw an exception
            throw new DirectoryCreationException(path, "Error while creating the .lit directory");
        }

        try {
            // Creating the settings, stats and versions files inside the .lit directory and loading them
            settingsDB = new JSONDocument<>(path + "/.lit/database/config.json");
            statsDB = new JSONDocument<>(path + "/.lit/database/stats.json");
            versionsDB = new JSONDocument<>(path + "/.lit/database/versions.json");

            // Setting the name, description and owner of the warehouse
            this.setName(name);
            this.setPath(path);
            this.setDescription(description);
            this.setOwner(owner);

        } catch (IOException ioe) {
            // Log the error
            System.out.println("Error while creating the config file");
            // Delete the directory
            Paths.get(path + "/.lit").toFile().delete();
        }
    }

    /**
     * Saves a new snapshot of the warehouse and stores it
     * @param name The name of the snapshot
     * @param contributors The contributors of the snapshot
     */
    public void saveVersion(String name, List<User> contributors) {

        if (Paths.get(this.getPath()).toFile().exists()) {
            // Log
            System.out.println("Saving version...");

            // Selecting all files and directories except the .lit folder
            FileFilter fileFilter = (file) -> !file.getName().equals(".lit");
            File[] versionContent = Paths.get(this.getPath()).toFile().listFiles(fileFilter);

            Version version = new Version(name, contributors);

            // Storing the version in the database
            versionsDB.getContent();

            // Creating the .lit/versions/{versionID} folder
            File versionDir = Paths.get(this.getPath() + "/.lit/versions/" + version.getId()).toFile();

            if (versionDir.mkdir()) {
                if (versionContent != null) {
                    for (File file : versionContent) {
                        try {
                            System.out.println("Copying file " + file.getName() + "...");
                            Files.copy(
                                    file.toPath(),
                                    Paths.get(versionDir.getPath() + "/" + file.getName()),
                                    REPLACE_EXISTING
                            );
                        } catch (IOException e) {
                            System.out.println("Error while copying file: " + e.getMessage());
                        }
                    }
                }
            } else {
                System.out.println("Error while creating version folder");
            }
        }
    }

    public void importFile(String filePath) {

    }

    public void restoreVersion() {

    }

    public void getCollaborators() {

    }

    public void getVersions() {

    }

    public void getFileStats() {

    }

    public void save(){

    }

    public void exportWarehouse() {

    }

    // Getters and setters
    public String getName() {
        return settingsDB.getContent().getString("name");
    }

    public void setName(String name) {
        if (settingsDB.getContent() == null) {
            settingsDB.setContent(new JSONObject());
        }
        settingsDB.getContent().put("name", name);
        settingsDB.save();
    }

    public String getPath() {
        return settingsDB.getContent().getString("path");
    }

    public void setPath(String path) {
        if (settingsDB.getContent() == null) {
            settingsDB.setContent(new JSONObject());
        }
        settingsDB.getContent().put("path", path);
        settingsDB.save();
    }

    public String getDescription() {
        return settingsDB.getContent().getString("description");
    }

    public void setDescription(String description) {
        if (settingsDB.getContent() == null) {
            settingsDB.setContent(new JSONObject());
        }
        settingsDB.getContent().put("description", description);
        settingsDB.save();
    }

    public User getOwner() {
        return new User(
                settingsDB.getContent().getJSONObject("user").getString("fullname"),
                settingsDB.getContent().getJSONObject("user").getString("username"),
                settingsDB.getContent().getJSONObject("user").getString("email")
        );
    }

    public void setOwner(User owner) {
        if (settingsDB.getContent() == null) {
            settingsDB.setContent(new JSONObject());
        }
        
        JSONObject user = new JSONObject();
        user.put("fullname", owner.getFullName());
        user.put("username", owner.getUsername());
        user.put("email", owner.getEmail());
        settingsDB.getContent().put("user", user);
    }

}
