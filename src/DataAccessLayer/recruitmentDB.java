package DataAccessLayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class recruitmentDB {
    private static Connection connect = DataConnection.connect;

    private static int numberOfRecruitment() {
        int count = 0;
        try {
            // Count the number of recruitments in the database
            PreparedStatement countRecruitment = connect.prepareStatement("SELECT COUNT(*) FROM recruitment");
            countRecruitment.execute();
            count = countRecruitment.getResultSet().next() ? countRecruitment.getResultSet().getInt(1) : 0;

            System.out.println("Number of recruitments: " + count);
            return count;
        } catch (SQLException e) {
            System.out.println("Failed to count the number of recruitments.");
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean createNewRecruitment(int recruitment_id, int company_id, String position, int quantity,
            Date startDate, Date endDate, String requirements, int recruitment_status) {
        try {
            // Insert a new recruitment into the database
            PreparedStatement insertRecruitment = connect.prepareStatement(
                    "INSERT INTO recruitment (recruitment_id, company_id, position, number_of_positions, start_date, end_date, requirements, recruitment_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            insertRecruitment.setInt(1, recruitment_id);
            insertRecruitment.setInt(2, company_id);
            insertRecruitment.setString(3, position);
            insertRecruitment.setInt(4, quantity);
            insertRecruitment.setDate(5, startDate);
            insertRecruitment.setDate(6, endDate);
            insertRecruitment.setString(7, requirements);
            insertRecruitment.setInt(8, recruitment_status);
            insertRecruitment.execute();

            System.out.println("Recruitment created successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to create a new recruitment.");
            e.printStackTrace();
            return false;
        }
    }
}
