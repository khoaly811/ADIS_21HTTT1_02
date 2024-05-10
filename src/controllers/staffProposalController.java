package controllers;

import java.sql.Date;
import com.jfoenix.controls.JFXButton;
import DataAccessLayer.recruitmentDB;
import dto.RecruitmentDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class staffProposalController {

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
    TableColumn<RecruitmentDTO, Void> actionCol;

    @FXML
    public void initialize() {
        posCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, String>("position"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Integer>("numberOfPosition"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Integer>("length"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, Date>("startDate"));
        jdCol.setCellValueFactory(new PropertyValueFactory<RecruitmentDTO, String>("requirement"));

        actionCol.setCellFactory(param -> new TableCell<>() {
            private final JFXButton appoveButton = new JFXButton("Approve");
            private final JFXButton deleteButton = new JFXButton("Delete");
            private final HBox buttonsBox = new HBox(appoveButton, deleteButton);
            {
                appoveButton.setId("approveBtn");
                deleteButton.setId("deleteBtn");
                appoveButton.setOnAction(event -> {
                    int recruitmentID = getTableView().getItems().get(getIndex()).getRecruitmentId().intValue();
                    // Handle edit action here
                    System.out.println("Approve " + recruitmentID);
                    recruitmentDB.updateRecruitmentStatus(recruitmentID, 1);
                });

                deleteButton.setOnAction(event -> {
                    RecruitmentDTO recruitment = getTableView().getItems().get(getIndex());
                    // Handle delete action here
                    System.out.println("Delete " + recruitment.getRecruitmentId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsBox);
                }
            }
        });

        tableViewProposal.setItems(recruitmentDB.getPendingRecruitments());
    }

    @FXML
    private void logout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/loginPage.fxml"));
            Stage window = (Stage) tableViewProposal.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
