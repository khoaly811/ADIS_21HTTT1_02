package controllers;

import com.jfoenix.controls.JFXButton;
import DataAccessLayer.DataConnection;
import DataAccessLayer.recruitmentDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createComProposalController {
    @FXML
    private JFXButton createBtn;

    @FXML
    private TextField quanTxtField;

    @FXML
    private TextField posTxtField;

    @FXML
    private TextField lengthTxtField;

    @FXML
    private TextField jobDesTxtField;

    @FXML
    private void createProposal() {
        String quantity = quanTxtField.getText();
        String position = posTxtField.getText();
        String length = lengthTxtField.getText();
        String jobDescription = jobDesTxtField.getText();

        if (quantity.isEmpty() || position.isEmpty() || length.isEmpty() || jobDescription.isEmpty()) {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields");
            alert.showAndWait();
            return;
        } else {
            if (recruitmentDB.createProposal(DataConnection.loginedAccount.getMemberID().intValue(), position,
                    Integer.valueOf(quantity), Integer.valueOf(length),
                    jobDescription)) {
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Proposal created successfully");
                alert.showAndWait();

                // Show proposal page
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../views/comProposalPage.fxml"));
                    Stage window = (Stage) createBtn.getScene().getWindow();
                    window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Show error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to create proposal");
                alert.showAndWait();
            }
        }
    }
}
