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

import java.sql.*;

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


    public static void viewDatabase() {
        String query = "SELECT * customers"; // Replace with your actual table name

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print column headers
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving data from the database.");
            e.printStackTrace();
        }
    }
}