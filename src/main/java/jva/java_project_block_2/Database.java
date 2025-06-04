/*package jva.java_project_block_2;

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
}*/
package jva.java_project_block_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/insurance_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Optional test method
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Connected to the database successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}

