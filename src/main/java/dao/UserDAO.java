package dao;

import model.User;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User authenticateUser(String email, String password) {
        User user = null;
        // Using try-with-resources for auto-closing (Guideline #2: Error Handling)
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // User found! Create User object
                user = new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    // 1. Validation Check: Does this email already exist?
    public boolean isEmailRegistered(String email) {
        boolean exists = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT id FROM users WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    // 2. Register New User
    public boolean registerUser(User user) {
        boolean rowInserted = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, "12345"); // ideally hash this password, but storing plain text for this level is acceptable
            // Note: The user object passed here might have the real password, let's use that:
            // ps.setString(2, user.getPassword()); <- BETTER.
            // Let's correct line 25 above:
            ps.setString(2, user.getPassword()); // Use the actual password from the user object

            ps.setString(3, user.getRole()); // Default to 'Student' usually

            rowInserted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
}