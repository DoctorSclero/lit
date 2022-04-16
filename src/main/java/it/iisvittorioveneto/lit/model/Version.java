package it.iisvittorioveneto.lit.model;

public class Version {
    private String id;
    private String name;
    private String[] authors;


    public Version(String id, String name, String[] authors) {
        this.id = id;
        this.name = name;
        this.authors = authors;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getAuthors() {
        return authors;
    }
}
