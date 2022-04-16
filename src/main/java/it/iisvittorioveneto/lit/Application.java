package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.model.User;

import java.nio.file.Paths;
import java.util.List;

public class Application {

    private List<Warehouse> openedWarehouses;

    public static void main(String[] args) {
        Application app = new Application();
        app.CreateWarehouse(
                "Test",
                "C:\\Users\\pietr\\Desktop\\test",
                "This is a test", new User("vittorio", "vittorio@gmail.com", "ciano"));
    }

    public void CreateWarehouse(String name, String path, String description, User owner) {
        // TODO: generate directory and config files for the new warehouse

        // Check if the path has a ".lit" directory inside
        if (Paths.get(path + "/.lit").toFile().exists()) {
            System.out.println("The path already contains a .lit directory");
        }

        new Warehouse(name, path, description, owner);
    }

    public void OpenWarehouse(String path) {
        // TODO: read all the files from the warehouse and create the warehouse object


    }

    public void CloseWarehouse(int id) {
        // TODO: close the warehouse and save all the changes
    }

    public Warehouse GetWarehouseById(int id) {
        return openedWarehouses.get(id);
    }

}
