package controllers;

import java.sql.Date;

import DataAccessLayer.recruitmentDB;
import dto.RecruitmentDTO;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class comProposalController {
    @FXML
    ImageView avaImgView;

    @FXML
    TableView<RecruitmentDTO> tableViewProposal;

    @FXML
    TableColumn<RecruitmentDTO, String> posCol;

    @FXML
    TableColumn<RecruitmentDTO, Integer> quantityCol;

    @FXML
    TableColumn<RecruitmentDTO, Integer> lengthCol;

    @FXML
    TableColumn<RecruitmentDTO, Date> startDateCol;

    @FXML
    TableColumn<RecruitmentDTO, String> jdCol;

    @FXML
    public void initialize() {
        posCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, String>("position"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Integer>("numberOfPosition"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Integer>("length"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Date>("startDate"));
        jdCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, String>("requirement"));

        tableViewProposal.setItems(recruitmentDB.getRecruitments());
    }

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
    private void showAddProposalPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/createProposalPage.fxml"));
            Stage window = (Stage) avaImgView.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
