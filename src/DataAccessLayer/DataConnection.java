package DataAccessLayer;

import java.math.BigInteger;
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

    public static MemberDTO loginAcc(String phoneNumber, String password) {
        try {
            // Check if the account exists in the database
            PreparedStatement loginAcc = connect
                    .prepareStatement("SELECT * FROM member WHERE phone = ? AND password = ?");
            loginAcc.setString(1, phoneNumber);
            loginAcc.setString(2, password);
            loginAcc.execute();

            // If the account exists, get the account information
            if (loginAcc.getResultSet().next()) {

                BigInteger memberID = BigInteger.valueOf(loginAcc.getResultSet().getLong(1));
                int memberType = loginAcc.getResultSet().getInt(2);
                String memberName = loginAcc.getResultSet().getString(3);
                String memberEmail = loginAcc.getResultSet().getString(4);
                String memberAddress = loginAcc.getResultSet().getString(5);
                String memberPhone = loginAcc.getResultSet().getString(6);
                String memberRepresentative = loginAcc.getResultSet().getString(7);
                String taxNumber = loginAcc.getResultSet().getString(8);
                String memberPassword = loginAcc.getResultSet().getString(9);

                // Create a new MemberDTO object
                MemberDTO member = new MemberDTO(memberID, memberType, memberName,
                        memberEmail, memberAddress, memberPhone,
                        memberRepresentative, taxNumber, memberPassword);

                System.out.println("Logged in successfully.");
                return member;
            } else {
                System.out.println("Account does not exist.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to login.");
            e.printStackTrace();
            return null;
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