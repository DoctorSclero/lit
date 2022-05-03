package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.database.JSONDocument;
import it.iisvittorioveneto.lit.exceptions.DirectoryCreationException;
import it.iisvittorioveneto.lit.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private List<Warehouse> openedWarehouses;
    private static Application instance;

    /**
     * Create a new warehouse singleton instance and returns it
     * @return The warehouse instance
     */
    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    /**
     * ! Only for testing purposes (not used in the application)
     */
    public static void main(String[] args) throws IOException {
        Application app = new Application();
        app.createWarehouse(
                "Test",
                "C:\\Users\\pietr\\Desktop\\test",
                "This is a test", new User("vittorio", "vittorio@gmail.com", "ciano"));
    }

    /**
     * Constructor
     */
    public Application() {
        openedWarehouses = new ArrayList<>();
    }

    /**
     * Create a new warehouse at the specified path with the specified name and description.
     * @param name The name of the new warehouse
     * @param path The path of the new warehouse
     * @param description The description of the new warehouse
     * @param owner The owner of the new warehouse
     * @throws InvalidPathException If the path is not valid
     * @throws DirectoryCreationException If the directory cannot be created
     */
    public void createWarehouse(String name, String path, String description, User owner)
            throws InvalidPathException, DirectoryCreationException {

        // If path does not exist, create it
        Path warehousePath = Paths.get(path);
        if (!warehousePath.toFile().exists()) {
            warehousePath.toFile().mkdir();
        }

        /*
         Directory structure creation
        */

        // Check if the path has a ".lit" directory inside
        if (Paths.get(path + "/.lit").toFile().exists()) {
            throw new InvalidPathException(path, "The path already contains a .lit directory");
        }

        // Create the .lit directory if error occurs delete the directory
        if (Paths.get(path + "/.lit").toFile().mkdir()) {
            // Create the settings, versions and database directory inside the .lit directory
            Paths.get(path + "/.lit/versions").toFile().mkdir();
            Paths.get(path + "/.lit/database").toFile().mkdir();

        } else {
            // Delete the directory
            Paths.get(path + "/.lit").toFile().delete();
            // Throw an exception
            throw new DirectoryCreationException(path, "Error while creating the .lit directory");
        }

        /*
         Configuration files creation
        */

        try {
            JSONDocument<JSONObject> settings = new JSONDocument<>(path + "/.lit/database/config.json");
            JSONDocument<JSONArray> versions = new JSONDocument<>(path + "/.lit/database/versions.json");
            JSONDocument<JSONObject> stats = new JSONDocument<>(path + "/.lit/database/stats.json");

        } catch (IOException ioe) {
            // Log the error
            System.out.println("Error while creating the config file");
            // Delete the directory
            Paths.get(path + "/.lit").toFile().delete();
        }

        this.openedWarehouses.add(new Warehouse(name, path, description, owner));
    }

    /**
     * Open the warehouse stored at the specified path
     * @param path The path of the warehouse to open
     */
    public void openWarehouse(String path) {
        // TODO: read all the files from the warehouse and create the warehouse object

    }

    public void closeWarehouse(int id) {
        // TODO: close the warehouse and save all the changes
    }

    public List<Warehouse> getOpenedWarehouses() {
        return openedWarehouses;
    }

    public Warehouse getWarehouse(int id) {
        return openedWarehouses.get(id);
    }

}
