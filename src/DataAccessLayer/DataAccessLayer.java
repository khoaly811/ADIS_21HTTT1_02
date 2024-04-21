package DataAccessLayer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccessLayer {
    private Connection connection;

    public DataAccessLayer() {
        connection = null;
    }

    void connect() throws ClassNotFoundException {
        String dbUrl = "jdbc:postgresql://dpg-co5q82ev3ddc7394t4d0-a.singapore-postgres.render.com:5432/pttk_hcmus_21";
        String username = "pttk_hcmus_21_user";
        String password = "1tuzk1DugJTHFUzXtiLbqiAX78u4nLDu";

        // Load the PostgreSQL JDBC driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
            return;
        }

        // Connect to the database
        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            System.out.println("Connected to the database.");

            // Perform database operations here
            // Example: executing SQL queries, updating data, etc.

            DatabaseMetaData dbmt = connection.getMetaData();
            ResultSet rs = dbmt.getCatalogs();
            while (rs.next()) {
                System.out.println("Catalog: " + rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}