package model;

public class User {
    private int id;
    private String email;
    private String password;
    private String role; // "Student", "Instructor", "Admin"

    public User(int id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    // Add this getter
    public String getPassword() {
        return password;
    }
    // While we are here, let's make sure we have the setter too (just in case)
    public void setPassword(String password) {
        this.password = password;
    }
}