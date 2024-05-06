package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class PaymentController {

    @FXML
    private TextField cardNumberTxtField;

    @FXML
    private TextField expirationDateTxtField;

    @FXML
    private TextField cvvTxtField;

    @FXML
    private Button payBtn;

    @FXML
    private void processPayment() throws Exception {
        String cardNumber = cardNumberTxtField.getText();
        String expirationDate = expirationDateTxtField.getText();
        String cvv = cvvTxtField.getText();

        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("CVV: " + cvv);

        Stage stage = (Stage) payBtn.getScene().getWindow();
        stage.close();
    }
}