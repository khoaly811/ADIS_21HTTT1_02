package DataAccessLayer;

import java.math.BigInteger;
import java.sql.Connection;
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

            if (getBiggestRecruitmentId.getResultSet().next()) {
                // Return the biggest recruitment id
                return getBiggestRecruitmentId.getResultSet().getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Failed to get the biggest recruitment id.");
            e.printStackTrace();
            return 0;
        }
    }

    // public static boolean createNewRecruitment(int recruitment_id, int
    // company_id, String position, int quantity,
    // Date startDate, Date endDate, String requirements, int recruitment_status) {
    // try {
    // // Insert a new recruitment into the database
    // PreparedStatement insertRecruitment = connect.prepareStatement(
    // "INSERT INTO recruitment (recruitment_id, company_id, position,
    // number_of_positions, start_date, end_date, requirements, recruitment_status)
    // VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
    // insertRecruitment.setInt(1, recruitment_id);
    // insertRecruitment.setInt(2, company_id);
    // insertRecruitment.setString(3, position);
    // insertRecruitment.setInt(4, quantity);
    // insertRecruitment.setDate(5, startDate);
    // insertRecruitment.setDate(6, endDate);
    // insertRecruitment.setString(7, requirements);
    // insertRecruitment.setInt(8, recruitment_status);
    // insertRecruitment.execute();

    // System.out.println("Recruitment created successfully.");
    // return true;
    // } catch (SQLException e) {
    // System.out.println("Failed to create a new recruitment.");
    // e.printStackTrace();
    // return false;
    // }
    // }

    public static boolean createProposal(int company_id, String position, int quantity, int length,
            String jobDescription) {
        try {
            // Insert a new proposal into the database
            PreparedStatement insertProposal = connect.prepareStatement(
                    "INSERT INTO recruitment (recruitment_id, company_id, position, number_of_positions, length, requirements, recruitment_status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertProposal.setInt(1, biggestRecruitmentId() + 1);
            insertProposal.setInt(2, company_id);
            insertProposal.setString(3, position);
            insertProposal.setInt(4, quantity);
            insertProposal.setInt(5, length);
            insertProposal.setString(6, jobDescription);
            insertProposal.setInt(7, 0);
            insertProposal.execute();

            System.out.println("Proposal created successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to create a new proposal.");
            e.printStackTrace();
            return false;
        }
    }

    public static ObservableList<RecruitmentDTO> getRecruitments(BigInteger memberID) {
        // Get all recruitments from the database sorted by recruitment status
        try {
            System.out.println("Member ID: " + memberID.toString());
            PreparedStatement getRecruitments = connect
                    .prepareStatement("SELECT * FROM recruitment  WHERE company_id = ? ORDER BY recruitment_status");
            getRecruitments.setInt(1, memberID.intValue());
            getRecruitments.execute();

            ObservableList<RecruitmentDTO> recruitments = FXCollections.observableArrayList();
            while (getRecruitments.getResultSet().next()) {
                recruitments.add(new RecruitmentDTO(getRecruitments.getResultSet().getInt(1),
                        getRecruitments.getResultSet().getInt(2), getRecruitments.getResultSet().getString(3),
                        getRecruitments.getResultSet().getInt(4), getRecruitments.getResultSet().getDate(5),
                        getRecruitments.getResultSet().getString(6), getRecruitments.getResultSet().getInt(7),
                        getRecruitments.getResultSet().getInt(8), getRecruitments.getResultSet().getInt(9)));
            }
            System.out.println("Recruitments retrieved successfully." + recruitments.toString());

            return recruitments;
        } catch (SQLException e) {
            System.out.println("Failed to get recruitments.");
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<RecruitmentDTO> getPendingRecruitments() {
        // Get all recruitments from the database
        try {
            PreparedStatement getRecruitments = connect
                    .prepareStatement("SELECT * FROM recruitment WHERE recruitment_status = 0");
            getRecruitments.execute();

            ObservableList<RecruitmentDTO> recruitments = FXCollections.observableArrayList();
            while (getRecruitments.getResultSet().next()) {
                recruitments.add(new RecruitmentDTO(getRecruitments.getResultSet().getInt(1),
                        getRecruitments.getResultSet().getInt(2), getRecruitments.getResultSet().getString(3),
                        getRecruitments.getResultSet().getInt(4), getRecruitments.getResultSet().getDate(5),
                        getRecruitments.getResultSet().getString(6), getRecruitments.getResultSet().getInt(7),
                        getRecruitments.getResultSet().getInt(8), getRecruitments.getResultSet().getInt(9)));
            }
            System.out.println("Recruitments retrieved successfully." + recruitments.toString());

            return recruitments;
        } catch (SQLException e) {
            System.out.println("Failed to get recruitments.");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateRecruitmentStatus(int recruitment_id, int status) {
        try {
            // Update the recruitment status in the database
            PreparedStatement updateRecruitmentStatus = connect
                    .prepareStatement("UPDATE recruitment SET recruitment_status = ? WHERE recruitment_id = ?");
            updateRecruitmentStatus.setInt(1, status);
            updateRecruitmentStatus.setInt(2, recruitment_id);
            updateRecruitmentStatus.execute();

            System.out.println("Recruitment status updated successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to update recruitment status.");
            e.printStackTrace();
            return false;
        }
    }

    public static RecruitmentDTO findRecruitmentByID(int recruitment_id) {
        try {
            PreparedStatement findRecruitment = connect
                    .prepareStatement("SELECT * FROM recruitment WHERE recruitment_id = ?");
            findRecruitment.setInt(1, recruitment_id);
            findRecruitment.execute();

            if (findRecruitment.getResultSet().next()) {
                return new RecruitmentDTO(findRecruitment.getResultSet().getInt(1),
                        findRecruitment.getResultSet().getInt(2), findRecruitment.getResultSet().getString(3),
                        findRecruitment.getResultSet().getInt(4), findRecruitment.getResultSet().getDate(5),
                        findRecruitment.getResultSet().getString(6), findRecruitment.getResultSet().getInt(7),
                        findRecruitment.getResultSet().getInt(8), findRecruitment.getResultSet().getInt(9));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to find recruitment by ID.");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateRecruitment(RecruitmentDTO recruitment) {
        try {
            // Convert util.Date to sql.Date
            java.sql.Date sqlStartDate = new java.sql.Date(recruitment.getStartDate().getTime());

            // Update the recruitment in the database
            PreparedStatement updateRecruitment = connect.prepareStatement(
                    "UPDATE recruitment SET start_date = ?, ads_form = ? WHERE recruitment_id = ?");
            updateRecruitment.setDate(1, sqlStartDate);
            updateRecruitment.setInt(2, recruitment.getAdsForm());
            updateRecruitment.setInt(3, recruitment.getRecruitmentId().intValue());
            updateRecruitment.execute();

            recruitmentDB.updateRecruitmentStatus(recruitment.getRecruitmentId().intValue(), 2);

            System.out.println("Recruitment updated successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to update recruitment.");
            e.printStackTrace();
            return false;
        }
    }
}
