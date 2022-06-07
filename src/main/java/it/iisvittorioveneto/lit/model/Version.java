package it.iisvittorioveneto.lit.model;

import it.iisvittorioveneto.lit.database.utils.JSONArray;
import it.iisvittorioveneto.lit.database.utils.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Version {

    private String id;
    private String name;
    private List<User> contributors;

    /**
     * Create a new version
     * @param name The version's name
     * @param contributors The version's contributors
     */
    public Version(String name, List<User> contributors){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.contributors = contributors;
    }

    public Version(JSONObject version) {
        this.id = version.getString("id");
        this.name = version.getString("name");
        this.contributors = new LinkedList<>();
        JSONArray storedContributors = version.getJSONArray("contributors");
        for (int i = 0; i < storedContributors.length(); i++) {
            this.contributors.add(new User(storedContributors.getJSONObject(i)));
        }
    }

    /**
     * Get the version's id
     * @return The version's id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the version's name
     * @return The version's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the version's contributors
     * @return The version's contributors
     */
    public List<User> getContributors() {
        return contributors;
    }

    /**
     * Get the version's JSON object
     * @return The version's JSON object
     */
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("contributors", new JSONArray());
        for (User contributor : contributors) {
            jsonObject.getJSONArray("contributors").put(contributor.toJSONObject());
        }
        return jsonObject;
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
