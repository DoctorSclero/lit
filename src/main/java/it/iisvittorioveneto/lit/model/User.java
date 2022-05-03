package it.iisvittorioveneto.lit.model;

import it.iisvittorioveneto.lit.Warehouse;
import it.iisvittorioveneto.lit.database.JSONDocument;

public class User {
    private String fullName;
    private String email;
    private String username;

    public User(String fullName, String email, String username) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
    }


}
