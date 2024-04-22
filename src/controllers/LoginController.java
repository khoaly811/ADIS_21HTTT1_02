package controllers;

import DataAccessLayer.DataConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
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
    private void login() throws Exception {
        String phone = phoneTxtField.getText();
        String password = passwordTxtField.getText();

        if (phone.equals("staff") && password.equals("staff")) {
            Parent root = FXMLLoader.load(getClass().getResource("../views/staffHomePage.fxml"));
            Stage window = (Stage) signinBtn.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } else if (DataConnection.loginAcc(phone, password) == 2) {
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
    private void showSignupPage() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/createComPage.fxml"));
        Stage window = (Stage) companyHyperlink.getScene().getWindow();
        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
    }
}
