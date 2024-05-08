package controllers;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import DataAccessLayer.recruitmentDB;
import dto.RecruitmentDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class updateProposalController implements Initializable {
    @FXML
    private Label posLabel;

    @FXML
    private Label quanLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private TextArea jdTextArea;

    @FXML
    private JFXComboBox<String> adFormCbb;

    @FXML
    private DatePicker startDatePicker;

    private int recruitmentId;
    private Map<String, Integer> adFormMap = new HashMap<>();

    public void setRecruitmentId(int recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    RecruitmentDTO recruitment = new RecruitmentDTO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(recruitmentId + " is the recruitment id.");
        recruitment = recruitmentDB.findRecruitmentByID(recruitmentId);
        posLabel.setText(recruitment.getPosition());
        quanLabel.setText(String.valueOf(recruitment.getNumberOfPosition()));
        lengthLabel.setText(String.valueOf(recruitment.getLength()));
        jdTextArea.setText(recruitment.getRequirement());
        jdTextArea.setEditable(false);
        adFormCbb.getItems().addAll("Newspaper", "Banner", "Online");
        adFormMap.put("Newspaper", 1);
        adFormMap.put("Banner", 2);
        adFormMap.put("Online", 3);

        adFormCbb.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(adFormMap.get(newValue));
        });
    }

    @FXML
    private void updateProposal() {
        // Create a new recruitment object
        if (startDatePicker.getValue() == null || adFormCbb.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Recruitment");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }
        recruitment
                .setStartDate(Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        System.out.println("Start date: " + recruitment.getStartDate());
        recruitment.setAdsForm(adFormMap.get(adFormCbb.getValue()));
        System.out.println("Ads form: " + recruitment.getAdsForm());

        // Update the recruitment object in the database
        if (recruitmentDB.updateRecruitment(recruitment)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Recruitment");
            alert.setHeaderText(null);
            alert.setContentText("Recruitment updated successfully.");
            alert.showAndWait();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("../views/comProposalPage.fxml"));
                Stage window = (Stage) startDatePicker.getScene().getWindow();
                window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to update recruitment.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Recruitment");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update recruitment.");
            alert.showAndWait();
        }
    }
}
