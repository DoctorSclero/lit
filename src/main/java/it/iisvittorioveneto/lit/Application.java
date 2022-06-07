package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.exceptions.DirectoryCreationException;
import it.iisvittorioveneto.lit.model.User;
import it.iisvittorioveneto.lit.utils.Zipper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
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

        //FileUtils.deleteDirectory(Paths.get("C:\\Users\\pietr\\Desktop\\lit-testing").toFile());

        app.openWarehouse("C:\\Users\\pietr\\Desktop\\lit-testing");
        //app.createWarehouse("lit-test", "C:\\Users\\pietr\\Desktop\\lit-testing", "wow",
        //        new User("Pietro", "EIfjsdf", "fkajwekddf"));
        //app.openWarehouse("C:\\Users\\pietr\\Desktop\\lit-testing");

        Warehouse warehouse = app.getWarehouse(0);


        LinkedList<User> users = new LinkedList<>();

        users.add(new User("vittorio", "effy", "vitto"));
        users.add(new User("renzo", "venice", "ree"));
        users.add(new User("enzuccio", "fuffy", "enzu"));

        warehouse.saveVersion(
                    "Test Version 1",
                    users
                );

        users.add(new User("lancester", "lancester", "lance"));

        warehouse.saveVersion(
                "Test Version 2",
                users
        );

        //warehouse.restoreVersion("62fefb10-8bf4-41d3-8382-e446d0bf4db8", new User("restore", "restore@restore.it", "restore"));
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
     * @param path The path of the new warehouse
     * @param description The description of the new warehouse
     * @param owner The owner of the new warehouse
     * @throws InvalidPathException If the path is not valid
     * @throws DirectoryCreationException If the directory cannot be created
     */
    public void createWarehouse(String name, String path, String description, User owner) throws InvalidPathException, DirectoryCreationException {
        this.openedWarehouses.add(new Warehouse(name, path, description, owner));
    }

    /**
     * Open the warehouse stored at the specified path
     * @param path The path of the warehouse to open
     */
    public void openWarehouse(String path) {
        this.openedWarehouses.add(new Warehouse(path));
    }

    public void closeWarehouse(int id) {
        this.getWarehouse(id).save();
        this.openedWarehouses.remove(id);

    }

    public String getWareHouseInfo(int id){
        StringBuilder res = new StringBuilder();
        Warehouse warehouse = this.getWarehouse(id);
        res.append("Nome: ").append(warehouse.getName()).append("\n");
        res.append("Descrizione: ").append(warehouse.getDescription()).append("\n");
        res.append("Proprietario: \nNome completo: ").append(warehouse.getOwner().getUsername());
        res.append(", email: ").append(warehouse.getOwner().getEmail()).append("\n");
        res.append("Percorso: ").append(warehouse.getPath()).append("\n");
        return res.toString();
    }

    public List<Warehouse> getOpenedWarehouses() {
        return openedWarehouses;
    }

    public Warehouse getWarehouse(int id) {
        return openedWarehouses.get(id);
    }

}
