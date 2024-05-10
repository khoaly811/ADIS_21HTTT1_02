package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import DataAccessLayer.recruitmentDB;
import dto.RecruitmentDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class viewProposalController implements Initializable {
    @FXML
    private Label posLabel;

    @FXML
    private Label quanLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private TextArea jdTextArea;

    @FXML
    private Label adsFormLabel;

    private int recruitmentId;

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
        startDateLabel.setText(recruitment.getStartDate().toString());
        String adForm = "";
        if (recruitment.getAdsForm() == 1) {
            adForm = "Newspaper";
        } else if (recruitment.getAdsForm() == 2) {
            adForm = "Banner";
        } else if (recruitment.getAdsForm() == 3) {
            adForm = "Online";
        }
        adsFormLabel.setText(adForm);
    }

    @FXML
    private void closePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/comProposalPage.fxml"));
            Stage window = (Stage) lengthLabel.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
