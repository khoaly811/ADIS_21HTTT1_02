package DataAccessLayer;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.MemberDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public static ObservableList<MemberDTO> getAllAccount() {
        try {
            // Get all the accounts in the database
            PreparedStatement getAllAcc = connect.prepareStatement("SELECT * FROM member");
            getAllAcc.execute();

            ObservableList<MemberDTO> accounts = FXCollections.observableArrayList();
            while (getAllAcc.getResultSet().next()) {
                accounts.add(new MemberDTO(BigInteger.valueOf(getAllAcc.getResultSet().getLong(1)),
                        getAllAcc.getResultSet().getInt(2), getAllAcc.getResultSet().getString(3),
                        getAllAcc.getResultSet().getString(4), getAllAcc.getResultSet().getString(5),
                        getAllAcc.getResultSet().getString(6), getAllAcc.getResultSet().getString(7),
                        getAllAcc.getResultSet().getString(8), getAllAcc.getResultSet().getString(9)));
            }

            System.out.println("All accounts retrieved.");
            return accounts;
        } catch (SQLException e) {
            System.out.println("Failed to get all accounts.");
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<MemberDTO> getAllComAccount() {
        try {
            // Get all the company accounts in the database
            PreparedStatement getAllComAcc = connect
                    .prepareStatement(
                            "SELECT * FROM member left join discount on member_id = company_id where member_type = 2");
            getAllComAcc.execute();

            for (int i = 1; i <= getAllComAcc.getMetaData().getColumnCount(); i++) {
                System.out.println(getAllComAcc.getMetaData().getColumnName(i));
            }
            ObservableList<MemberDTO> accounts = FXCollections.observableArrayList();

            while (getAllComAcc.getResultSet().next()) {
                accounts.add(new MemberDTO(BigInteger.valueOf(getAllComAcc.getResultSet().getLong(1)),
                        getAllComAcc.getResultSet().getString(3),
                        getAllComAcc.getResultSet().getString(4),
                        getAllComAcc.getResultSet().getString(6),
                        getAllComAcc.getResultSet().getString(8),
                        getAllComAcc.getResultSet().getString(11),
                        getAllComAcc.getResultSet().getInt(12)));
            }
            System.out.println("All company accounts retrieved.");

            for (MemberDTO account : accounts) {
                System.out.println(account.getMemberName() + " " + account.getMemberEmail() + " "
                        + account.getMemberPhone() + " " + account.getDiscount() + " " + account.getDescription());
            }
            return accounts;
        } catch (SQLException e) {
            System.out.println("Failed to get all company accounts.");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateAccount(MemberDTO account) {
        try {
            // Update the account in the database
            PreparedStatement updateAcc = connect.prepareStatement(
                    "UPDATE member SET member_name = ?, email = ?, address = ?, phone = ?, representative = ?, tax_number = ?, password = ? WHERE member_id = ?");

            // Set the values for the account
            updateAcc.setString(1, account.getMemberName());
            updateAcc.setString(2, account.getMemberEmail());
            updateAcc.setString(3, account.getMemberAddress());
            updateAcc.setString(4, account.getMemberPhone());
            updateAcc.setString(5, account.getMemberRepresentative());
            updateAcc.setString(6, account.getTaxNumber());
            updateAcc.setString(7, account.getMemberPassword());
            updateAcc.setLong(8, account.getMemberID().longValue());

            // Execute the query
            updateAcc.executeUpdate();

            discountDB.addDiscount(account);

            System.out.println("Account updated.");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to update account.");
            e.printStackTrace();
            return false;
        }
    }

    public static MemberDTO findMemberbyID(Integer id) {
        try {
            // Find the account by ID
            PreparedStatement findMember = connect.prepareStatement("SELECT * FROM member WHERE member_id = ?");
            findMember.setInt(1, id);
            findMember.execute();

            if (findMember.getResultSet().next()) {
                BigInteger memberID = BigInteger.valueOf(findMember.getResultSet().getLong(1));
                int memberType = findMember.getResultSet().getInt(2);
                String memberName = findMember.getResultSet().getString(3);
                String memberEmail = findMember.getResultSet().getString(4);
                String memberAddress = findMember.getResultSet().getString(5);
                String memberPhone = findMember.getResultSet().getString(6);
                String memberRepresentative = findMember.getResultSet().getString(7);
                String taxNumber = findMember.getResultSet().getString(8);
                String memberPassword = findMember.getResultSet().getString(9);

                // Create a new MemberDTO object
                MemberDTO member = new MemberDTO(memberID, memberType, memberName,
                        memberEmail, memberAddress, memberPhone,
                        memberRepresentative, taxNumber, memberPassword);

                System.out.println("Account found.");
                return member;
            } else {
                System.out.println("Account not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to find account.");
            e.printStackTrace();
            return null;
        }
    }
}