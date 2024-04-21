package controllers;

import adis02.App;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class LoginController {
    @FXML
    private TextField usernameTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Button signinBtn;

    @FXML
    private Hyperlink employeeHyperLink;

    @FXML
    private Hyperlink companyHyperLink;

    @FXML
    private void login() throws Exception {
        String username = usernameTxtField.getText();
        String password = passwordTxtField.getText();

        if (username.equals("staff") && password.equals("staff")) {
            Parent root = FXMLLoader.load(getClass().getResource("../views/staffHomePage.fxml"));
            Stage window = (Stage) signinBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        }
        if (username.equals("company") && password.equals("company")) {
            Parent root = FXMLLoader.load(getClass().getResource("../views/companyHomePage.fxml"));
            Stage window = (Stage) signinBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } else {
            System.out.println("Invalid username or password");
        }
    }
}
