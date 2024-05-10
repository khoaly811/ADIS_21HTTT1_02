package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import DataAccessLayer.comAccDB;
import dto.MemberDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updateComController implements Initializable {
    @FXML
    private TextField comNameTxtField;

    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField phoneTxtField;

    @FXML
    private TextField discountTxtField;

    @FXML
    private TextField desTxtField;

    @FXML
    private JFXButton saveBtn;

    private int comId;

    public void setComId(int comId) {
        this.comId = comId;
        System.out.println(comId + " is the com id to be updated.");
    }

    MemberDTO member = new MemberDTO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(comId + " is the com id to be updated in initialize.");
        member = comAccDB.findMemberbyID(comId);
        comNameTxtField.setText(member.getMemberName());
        emailTxtField.setText(member.getMemberEmail());
        phoneTxtField.setText(member.getMemberPhone());
        discountTxtField.setText(String.valueOf((member.getDiscount() == null) ? "0" : member.getDiscount()));
        desTxtField.setText(member.getDescription());
    }

    @FXML
    public void saveCom() {
        member.setMemberName(comNameTxtField.getText());
        member.setMemberEmail(emailTxtField.getText());
        member.setMemberPhone(phoneTxtField.getText());
        member.setDiscount(Integer.parseInt(discountTxtField.getText()));
        member.setDescription(desTxtField.getText());

        if (comAccDB.updateAccount(member)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Company Account");
            alert.setHeaderText(null);
            alert.setContentText("Company account updated successfully.");
            alert.showAndWait();

            // Show company account list
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../views/staffComPage.fxml"));
                Stage window = (Stage) desTxtField.getScene().getWindow();
                window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Company Account");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update company account.");
            alert.showAndWait();
        }
    }
}
