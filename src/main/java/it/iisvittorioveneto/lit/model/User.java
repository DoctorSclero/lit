package it.iisvittorioveneto.lit.model;

import it.iisvittorioveneto.lit.Warehouse;
import it.iisvittorioveneto.lit.database.JSONDocument;
import it.iisvittorioveneto.lit.database.JSONObjectDocument;
import it.iisvittorioveneto.lit.database.utils.JSONObject;

public class User {
    private String fullName;
    private String email;
    private String username;

    public User(String fullName, String email, String username) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JSONObject toJSONObject() {
        JSONObject user = new JSONObject();
        user.put("fullname", fullName);
        user.put("username", username);
        user.put("email", email);
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
