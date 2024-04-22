package controllers;

import java.util.Optional;

import DataAccessLayer.DataConnection;
import dto.MemberDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SignupController {
    @FXML
    TextField comNameTxtField;

    @FXML
    TextField addressTxtField;

    @FXML
    TextField taxNumTxtField;

    @FXML
    TextField emailTxtField;

    @FXML
    TextField phoneTxtField;

    @FXML
    TextField representativeTxtField;

    @FXML
    TextField passwordTxtField;

    @FXML
    TextField confirmPasswordTxtField;

    @FXML
    private void createComAcc() {
        String comName = comNameTxtField.getText();
        String address = addressTxtField.getText();
        String taxNum = taxNumTxtField.getText();
        String email = emailTxtField.getText();
        String phone = phoneTxtField.getText();
        String representative = representativeTxtField.getText();
        String password = passwordTxtField.getText();
        String confirmPass = confirmPasswordTxtField.getText();

        if (comName.isEmpty() || address.isEmpty() || taxNum.isEmpty() || email.isEmpty() || phone.isEmpty()
                || representative.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        if (password.equals(confirmPass) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passwords do not match");
            alert.setContentText(confirmPass + " " + password);
            return;
        } else {
            MemberDTO newComAcc = new MemberDTO(2, comName, address, phone, representative, email, taxNum, password);
            if (DataConnection.createNewComAcc(newComAcc)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Company account created successfully");

                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == okButton) {
                    System.out.println("Custom Button Clicked!");
                    // Add your custom action here
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("../views/loginPage.fxml"));
                        Stage window = (Stage) comNameTxtField.getScene().getWindow();
                        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Alert Closed!");
                }
            } else {
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getCode().toString().equals("ENTER")) {
            createComAcc();
        }
    }

    @FXML
    private void showLoginPage() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/loginPage.fxml"));
        Stage window = (Stage) comNameTxtField.getScene().getWindow();
        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
    }
}
