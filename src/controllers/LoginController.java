package controllers;

import DataAccessLayer.comAccDB;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField phoneTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Button signinBtn;

    @FXML
    private Hyperlink employeeHyperlink;

    @FXML
    private Hyperlink companyHyperlink;

    @FXML
    private void initialize() {
        Platform.runLater(() -> phoneTxtField.requestFocus());
        phoneTxtField.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                passwordTxtField.requestFocus();
            }
        });
        passwordTxtField.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                try {
                    login();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void login() throws Exception {
        String phone = phoneTxtField.getText();
        String password = passwordTxtField.getText();

        // String phone = "staff";
        // String password = "staff";

        if (phone.isEmpty() || password.isEmpty()) {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields");
            alert.showAndWait();
            return;
        } else if (phone.equals("staff") && password.equals("staff")) {
            Parent root = FXMLLoader.load(getClass().getResource("../views/staffHomePage.fxml"));
            Stage window = (Stage) signinBtn.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } else if (comAccDB.loginAcc(phone, password) != null) {
            if (comAccDB.loginAcc(phone, password).getMemberType() == 2) {
                // Company account
                // Redirect to company home page
                Parent root = FXMLLoader.load(getClass().getResource("../views/comHomePage.fxml"));
                Stage window = (Stage) signinBtn.getScene().getWindow();
                window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
            } else {
                // Employee account
                // Redirect to employee home page
                Parent root = FXMLLoader.load(getClass().getResource("../views/employeeHomePage.fxml"));
                Stage window = (Stage) signinBtn.getScene().getWindow();
                window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
            }
        } else {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username or password");
            alert.showAndWait();
        }
    }

    @FXML
    private void showSignupPage() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/createComPage.fxml"));
        Stage window = (Stage) companyHyperlink.getScene().getWindow();
        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
    }
}
