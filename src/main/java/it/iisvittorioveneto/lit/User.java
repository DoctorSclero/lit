package it.iisvittorioveneto.lit;

public class User {
    private String fullName;
    private String email;
    private String username;

    public User(String fullName, String email, String username) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
    }

    public void createWarehouse(String name, String path) {
        Warehouse warehouse = new Warehouse(name, path);
    }
}
