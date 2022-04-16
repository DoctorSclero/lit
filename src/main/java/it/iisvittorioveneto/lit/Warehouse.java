package it.iisvittorioveneto.lit;

public class Warehouse {
    private String name;
    private String path;

    public Warehouse(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    /*
     * Initializes the warehouse creating the folder if it doesn't exist
     * and storing the default configuration file in a hidden
     * directory named ".lit"
     */
    public void init() {
        this.name = name;
        this.path = path;
    }

    public void loadFromLitDirectory() {

    }

}