package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.model.User;

public class Warehouse {
    private String name;
    private String path;
    private String description;
    private User owner;

    public Warehouse(String name, String path, String description, User owner) {
        this.name = name;
        this.path = path;
        this.description = description;
        this.owner = owner;
    }

    public void importFile(String filePath) {

    }

    public void saveVersion() {

    }

    public void restoreVersion() {

    }

    public void getGeneralStats() {

    }

    public void getFileStats() {

    }

    public void getCollaboratorsStats() {

    }

    public void getInfo() {

    }

    public void exportWarehouse() {

    }

}
