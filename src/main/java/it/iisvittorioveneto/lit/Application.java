package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.database.JSONDocument;
import it.iisvittorioveneto.lit.exceptions.DirectoryCreationException;
import it.iisvittorioveneto.lit.model.User;
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

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.createWarehouse(
                "Test",
                "C:\\Users\\pietr\\Desktop\\test",
                "This is a test", new User("vittorio", "vittorio@gmail.com", "ciano"));
    }

    // Public Constructor
    public Application() {
        openedWarehouses = new ArrayList<>();
    }

    public void createWarehouse(String name, String path, String description, User owner)
            throws InvalidPathException, DirectoryCreationException, IOException {
        // TODO: generate directory and config files for the new warehouse

        // If the path exists
        Path warehousePath = Paths.get(path);

        if (warehousePath.toFile().exists()) {
            // TODO implement the exception
        }

        // Check if the path has a ".lit" directory inside
        if (Paths.get(path + "/.lit").toFile().exists()) {
            throw new InvalidPathException(path, "The path already contains a .lit directory");
        }

        // Create the .lit directory if error occurs delete the directory
        if (Paths.get(path + "/.lit").toFile().mkdir()) {
            // Create the settings, versions and stats directory inside the .lit directory
            Paths.get(path + "/.lit/versions").toFile().mkdir();
            Paths.get(path + "/.lit/stats").toFile().mkdir();
        } else {
            // Delete the directory
            Paths.get(path + "/.lit").toFile().delete();
            // Throw an exception
            throw new DirectoryCreationException(path, "Error while creating the .lit directory");
        }

        // TODO: create the json config file

        try {
            JSONDocument<JSONObject> settings = new JSONDocument<>(path + "/.lit/config.json");
        } catch (IOException ioe) {
            throw new IOException("Error while creating the config file");
        }

        new Warehouse(name, path, description, owner);
    }

    public Warehouse openWarehouse(String path) {
        // TODO: read all the files from the warehouse and create the warehouse object

        return null;
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
