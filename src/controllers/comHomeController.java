package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class comHomeController {
    @FXML
    ImageView avaImgView;

    @FXML
    private void logout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/loginPage.fxml"));
            Stage window = (Stage) avaImgView.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showProposalPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/comProposalPage.fxml"));
            Stage window = (Stage) avaImgView.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}