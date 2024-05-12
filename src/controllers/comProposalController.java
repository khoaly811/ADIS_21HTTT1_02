package controllers;

import java.sql.Date;

import com.jfoenix.controls.JFXButton;

import DataAccessLayer.DataConnection;
import DataAccessLayer.recruitmentDB;
import dto.RecruitmentDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
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
    TableColumn<RecruitmentDTO, String> adsFormCol;

    @FXML
    TableColumn<RecruitmentDTO, Void> actionCol;

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
                            return new SimpleStringProperty("Pending approval");
                        } else if (recruitmentDTO.getRecruitmentStatus() == 1) {
                            return new SimpleStringProperty("Approved");
                        } else if (recruitmentDTO.getRecruitmentStatus() == 2) {
                            return new SimpleStringProperty("Done");
                        } else {
                            return new SimpleStringProperty("Rejected");
                        }
                    }
                });

        adsFormCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<RecruitmentDTO, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<RecruitmentDTO, String> params) {
                        RecruitmentDTO recruitmentDTO = params.getValue();
                        System.out.println(recruitmentDTO.getAdsForm());
                        if (recruitmentDTO.getAdsForm() == 1) {
                            return new SimpleStringProperty("Newspaper");
                        } else if (recruitmentDTO.getAdsForm() == 2) {
                            return new SimpleStringProperty("Banner");
                        } else if (recruitmentDTO.getAdsForm() == 3) {
                            return new SimpleStringProperty("Online");
                        } else {
                            return new SimpleStringProperty("");
                        }
                    }
                });

        actionCol.setCellFactory(param -> new TableCell<>() {
            // If status is pending, show edit button
            private final JFXButton editButton = new JFXButton("Edit");
            {
                editButton.setId("editBtn");
                editButton.setOnAction(event -> {
                    int recruitmentID = getTableView().getItems().get(getIndex()).getRecruitmentId().intValue();
                    // Handle edit action here
                    System.out.println("Edit " + recruitmentID);
                    try {
                        // Create an instance of the controller
                        updateProposalController controller = new updateProposalController();

                        controller.setRecruitmentId(recruitmentID);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/updateProposalPage.fxml"));
                        loader.setController(controller);

                        Parent root = loader.load();
                        Stage window = (Stage) avaImgView.getScene().getWindow();
                        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            private final JFXButton viewButton = new JFXButton("View");
            {
                viewButton.setId("viewBtn");
                viewButton.setOnAction(event -> {
                    int recruitmentID = getTableView().getItems().get(getIndex()).getRecruitmentId().intValue();
                    try {
                        // Create an instance of the controller
                        viewProposalController controller = new viewProposalController();

                        controller.setRecruitmentId(recruitmentID);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/viewProposalPage.fxml"));
                        loader.setController(controller);

                        Parent root = loader.load();
                        Stage window = (Stage) avaImgView.getScene().getWindow();
                        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    int status = getTableView().getItems().get(getIndex()).getRecruitmentStatus();
                    System.out.println(status + " is the status");
                    if (status == 1) {
                        setGraphic(editButton);
                    } else if (status == 2) {
                        System.out.println("View button");
                        setGraphic(viewButton);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });

        tableViewProposal.setItems(recruitmentDB.getRecruitments(DataConnection.loginedAccount.getMemberID()));
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
