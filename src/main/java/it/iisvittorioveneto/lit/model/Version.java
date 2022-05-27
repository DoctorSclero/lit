package it.iisvittorioveneto.lit.model;

import it.iisvittorioveneto.lit.Warehouse;
import it.iisvittorioveneto.lit.database.JSONDocument;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Version {

    private String id;
    private String name;
    private List<User> contributors;

    public Version(String name, List<User> contributors){

        this.id = UUID.randomUUID().toString();
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
