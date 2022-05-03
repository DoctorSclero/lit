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

    public void getCollaborators() {

    }

    public void getVersions() {

    }

    public void getFileStats() {

    }

    public void exportWarehouse() {

    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
