package DataAccessLayer;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dto.MemberDTO;

public class comAccDB {
    private static Connection connect = DataConnection.connect;

    private static int biggestID() {
        try {
            // Get the biggest member ID in the database
            PreparedStatement biggestID = connect.prepareStatement("SELECT MAX(member_id) FROM member");
            biggestID.execute();

            if (biggestID.getResultSet().next()) {
                return biggestID.getResultSet().getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Failed to get the biggest member ID.");
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean createNewComAcc(MemberDTO companyAccount) {
        try {
            // Insert the new company account into the database
            PreparedStatement createComAcc = connect.prepareStatement(
                    "INSERT INTO member (member_id,member_type, member_name, address, phone, representative, email, password, tax_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Set the values for the new company account
            createComAcc.setInt(1, biggestID() + 1);
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

                DataConnection.loginedAccount = member;

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
}