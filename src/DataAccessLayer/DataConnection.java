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

    private static int numberOfAcc() {
        int count = 0;
        try {
            // Count the number of accounts in the database
            PreparedStatement countAcc = connect.prepareStatement("SELECT COUNT(*) FROM member");
            countAcc.execute();
            count = countAcc.getResultSet().next() ? countAcc.getResultSet().getInt(1) : 0;

            System.out.println("Number of accounts: " + count);
        } catch (SQLException e) {
            System.out.println("Failed to count the number of accounts.");
            e.printStackTrace();
        }
        return count;
    }

    public static boolean createNewComAcc(MemberDTO companyAccount) {
        try {
            // Insert the new company account into the database
            PreparedStatement createComAcc = connect.prepareStatement(
                    "INSERT INTO member (member_id,member_type, member_name, address, phone, representative, email, password, tax_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Set the values for the new company account
            createComAcc.setInt(1, numberOfAcc() + 1);
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
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to create new company account.");
            e.printStackTrace();
            return false;
        }
    }

    public static int loginAcc(String phoneNumber, String password) {
        int memberType = -1;
        try {
            // Check if the account exists in the database
            PreparedStatement loginAcc = connect
                    .prepareStatement("SELECT member_type FROM member WHERE phone = ? AND password = ?");
            loginAcc.setString(1, phoneNumber);
            loginAcc.setString(2, password);
            loginAcc.execute();

            // Get the member type of the account
            loginAcc.getResultSet().next();
            memberType = loginAcc.getResultSet().getInt(1);

            System.out.println("Member type: " + memberType);
            return memberType;
        } catch (SQLException e) {
            System.out.println("Failed to login.");
            e.printStackTrace();
            return -1;
        }
    }
}