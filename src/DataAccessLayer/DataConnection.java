package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.MemberDTO;

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

    public static void createNewComAcc(MemberDTO companyAccount) {
        try {
            // Insert the new company account into the database
            PreparedStatement createComAcc = connect.prepareStatement(
                    "INSERT INTO member (member_id, member_type, member_name, address, phone, representative, email, password, taxNum) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Set the values for the new company account
            createComAcc.setInt(1, 2);
            createComAcc.setInt(2, 2);
            createComAcc.setString(3, companyAccount.getMemberName());
            createComAcc.setString(4, companyAccount.getMemberAddress());
            createComAcc.setString(5, companyAccount.getMemberPhone());
            createComAcc.setString(6, companyAccount.getMemberRepresentative());
            createComAcc.setString(7, companyAccount.getMemberEmail());
            createComAcc.setString(8, companyAccount.getMemberPassword());
            createComAcc.setString(9, companyAccount.getTaxNumber());

            // Execute the query
            createComAcc.executeUpdate();

            System.out.println("New company account created.");
        } catch (SQLException e) {
            System.out.println("Failed to create new company account.");
            e.printStackTrace();
        }
    }
}