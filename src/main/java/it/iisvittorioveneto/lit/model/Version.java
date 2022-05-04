package it.iisvittorioveneto.lit.model;

import java.util.List;

public class Version {
    private String id;
    private String name;
    private List<User> contributors;

    public Version(String id, String name, List<User> contributors) {
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

    public List<User> getContributors() {
        return contributors;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contributors=" + contributors +
                '}';
    }
}
