package it.iisvittorioveneto.lit.model;

import it.iisvittorioveneto.lit.Warehouse;
import it.iisvittorioveneto.lit.database.JSONDocument;
import it.iisvittorioveneto.lit.database.JSONObjectDocument;
import it.iisvittorioveneto.lit.database.utils.JSONObject;

public class User {
    private String fullName;
    private String email;
    private String username;

    /**
     * Create a new user
     * @param fullName The user's full name
     * @param email The user's email
     * @param username The user's username
     */
    public User(String fullName, String email, String username) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
    }

    /**
     * Get the user's full name
     * @param jsonUser The user's JSON object
     */
    public User(JSONObject jsonUser) {
        this.fullName = jsonUser.getString("fullName");
        this.email = jsonUser.getString("email");
        this.username = jsonUser.getString("username");
    }

    /**
     * Get the user's full name
     * @return The user's full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the user's full name
     * @param fullName The new user's full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Get the user's email
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email
     * @param email The new user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user's username
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user's username
     * @param username The new user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's JSON object
     * @return The user's JSON object
     */
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
