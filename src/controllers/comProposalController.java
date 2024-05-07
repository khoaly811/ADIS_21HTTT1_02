package controllers;

import java.sql.Date;
import DataAccessLayer.recruitmentDB;
import dto.RecruitmentDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    TableColumn<RecruitmentDTO, String> statusCol;

    @FXML
    public void initialize() {
        posCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, String>("position"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Integer>("numberOfPosition"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Integer>("length"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Date>("startDate"));
        jdCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, String>("requirement"));
        statusCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<RecruitmentDTO, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<RecruitmentDTO, String> params) {
                        RecruitmentDTO recruitmentDTO = params.getValue();
                        if (recruitmentDTO.getRecruitmentStatus() == 0) {
                            return new SimpleStringProperty("Pending");
                        } else if (recruitmentDTO.getRecruitmentStatus() == 1) {
                            return new SimpleStringProperty("Approved");
                        } else {
                            return new SimpleStringProperty("Rejected");
                        }
                    }
                });
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
