package controllers;

import DataAccessLayer.DataConnection;
import dto.MemberDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    private void createComAcc() {
        String comName = comNameTxtField.getText();
        String address = addressTxtField.getText();
        String taxNum = taxNumTxtField.getText();
        String email = emailTxtField.getText();
        String phone = phoneTxtField.getText();
        String representative = representativeTxtField.getText();

        MemberDTO newComAcc = new MemberDTO(2, comName, address, phone, representative, email, taxNum);
        DataConnection.createNewComAcc(newComAcc);  
    }
}
