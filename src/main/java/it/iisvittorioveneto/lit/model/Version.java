package it.iisvittorioveneto.lit.model;

public class Version {
    private String id;
    private String name;
    private String[] contributors;

    public Version(String id, String name, String[] contributors) {
        this.id = id;
        this.name = name;
        this.contributors = contributors;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getContributors() {
        return contributors;
    }
}
