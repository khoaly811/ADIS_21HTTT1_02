package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    public static Connection connect;

    public static void connect() throws ClassNotFoundException {
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
        try {
            connect = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected to the database.");
            return;
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
            return;
        }
    }

    public static void disconnect() {
        try {
            connect.close();
            System.out.println("Disconnected from the database.");
        } catch (SQLException e) {
            System.out.println("Failed to disconnect from the database.");
            e.printStackTrace();
        }
    }
}