package DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dto.MemberDTO;

public class discountDB {
    private static Connection connect = DataConnection.connect;

    private static int biggestID() {
        int biggestID = 0;
        try {
            PreparedStatement statement = connect.prepareStatement("SELECT MAX(discount_id) FROM discount");
            statement.execute();

            if (statement.getResultSet().next()) {
                biggestID = statement.getResultSet().getInt(1);
            } else {
                biggestID = 0;
            }
            return biggestID;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean addDiscount(MemberDTO account) {
        try {
            // If not find the account in the discount table, insert new record
            PreparedStatement findDiscount = connect.prepareStatement("SELECT * FROM discount WHERE company_id = ?");
            findDiscount.setLong(1, account.getMemberID().longValue());
            findDiscount.execute();

            if (!findDiscount.getResultSet().next()) {
                PreparedStatement insertDiscount = connect
                        .prepareStatement(
                                "INSERT INTO discount(discount_id, company_id, discount_percentage, description) VALUES(?, ?, ?, ?)");
                insertDiscount.setInt(1, biggestID() + 1);
                insertDiscount.setLong(2, account.getMemberID().longValue());
                insertDiscount.setInt(3, account.getDiscount());
                insertDiscount.setString(4, account.getDescription());
                insertDiscount.executeUpdate();

                return true;
            } else {
                PreparedStatement updateDiscount = connect
                        .prepareStatement(
                                "UPDATE discount SET discount_percentage = ?, description = ? WHERE company_id = ?");
                updateDiscount.setInt(1, account.getDiscount());
                updateDiscount.setString(2, account.getDescription());
                updateDiscount.setLong(3, account.getMemberID().longValue());
                updateDiscount.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
