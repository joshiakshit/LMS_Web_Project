package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // 1. Database Config (CHANGE THESE TO MATCH YOUR MYSQL SETTINGS)
    private static final String URL = "jdbc:mysql://localhost:3306/lms_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678"; // Put your real MySQL password here

    // 2. Static method to get connection (Singleton pattern)
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load the driver class (Required for web apps)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("CRITICAL: MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("CRITICAL: Connection failed! Check URL/User/Pass.");
            e.printStackTrace();
        }
        return con;
    }
}