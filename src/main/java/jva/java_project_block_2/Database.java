package jva.java_project_block_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Database credentials and URL
    private static final String URL = "jdbc:mysql://localhost:3306/insurance_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    public static void connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to the database successfully!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}
