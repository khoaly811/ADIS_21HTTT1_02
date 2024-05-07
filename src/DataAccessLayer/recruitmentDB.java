package DataAccessLayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.RecruitmentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class recruitmentDB {
    private static Connection connect = DataConnection.connect;

    private static int biggestRecruitmentId() {
        try {
            // Get the biggest recruitment id from the database
            PreparedStatement getBiggestRecruitmentId = connect
                    .prepareStatement("SELECT MAX(recruitment_id) FROM recruitment");
            getBiggestRecruitmentId.execute();

            return getBiggestRecruitmentId.getResultSet().getInt(1);
        } catch (SQLException e) {
            System.out.println("Failed to get the biggest recruitment id.");
            e.printStackTrace();
            return 0;
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

    public static boolean createProposal(int company_id, String position, int quantity, int length,
            String jobDescription) {
        try {
            // Insert a new proposal into the database
            PreparedStatement insertProposal = connect.prepareStatement(
                    "INSERT INTO recruitment (recruitment_id, company_id, position, number_of_positions, length, requirements) VALUES (?, ?, ?, ?, ?, ?)");
            insertProposal.setInt(1, biggestRecruitmentId() + 1);
            insertProposal.setInt(2, company_id);
            insertProposal.setString(3, position);
            insertProposal.setInt(4, quantity);
            insertProposal.setInt(5, length);
            insertProposal.setString(6, jobDescription);
            insertProposal.execute();

            System.out.println("Proposal created successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to create a new proposal.");
            e.printStackTrace();
            return false;
        }
    }

    public static ObservableList<RecruitmentDTO> getRecruitments() {
        // Get all recruitments from the database
        try {
            PreparedStatement getRecruitments = connect.prepareStatement("SELECT * FROM recruitment");
            getRecruitments.execute();

            ObservableList<RecruitmentDTO> recruitments = FXCollections.observableArrayList();
            while (getRecruitments.getResultSet().next()) {
                recruitments.add(new RecruitmentDTO(getRecruitments.getResultSet().getInt(1),
                        getRecruitments.getResultSet().getInt(2), getRecruitments.getResultSet().getString(3),
                        getRecruitments.getResultSet().getInt(4), getRecruitments.getResultSet().getDate(5),
                        getRecruitments.getResultSet().getString(6), getRecruitments.getResultSet().getInt(7),
                        getRecruitments.getResultSet().getInt(8)));
            }
            System.out.println("Recruitments retrieved successfully." + recruitments.toString());

            return recruitments;
        } catch (SQLException e) {
            System.out.println("Failed to get recruitments.");
            e.printStackTrace();
            return null;
        }
    }
}
