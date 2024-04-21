package controllers;

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
    private TextField usernameTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Button signinBtn;

    @FXML
    private Hyperlink employeeHyperlink;

    @FXML
    private Hyperlink companyHyperlink;

    @FXML
    private void login() throws Exception {
        String username = usernameTxtField.getText();
        String password = passwordTxtField.getText();

        if (username.equals("staff") && password.equals("staff")) {
            Parent root = FXMLLoader.load(getClass().getResource("../views/staffHomePage.fxml"));
            Stage window = (Stage) signinBtn.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } else if (username.equals("company") && password.equals("company")) {
            Parent root = FXMLLoader.load(getClass().getResource("../views/companyHomePage.fxml"));
            Stage window = (Stage) signinBtn.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } else {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username or password");
            alert.showAndWait();
        }
    }

    @FXML
    private void createComAcc() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/createComPage.fxml"));
        Stage window = (Stage) companyHyperlink.getScene().getWindow();
        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
    }
}
