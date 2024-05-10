package controllers;

import com.jfoenix.controls.JFXButton;

import DataAccessLayer.comAccDB;
import dto.MemberDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class staffComPageController {
    @FXML
    TableView<MemberDTO> comTableView;

    @FXML
    TableColumn<MemberDTO, String> comCol;

    @FXML
    TableColumn<MemberDTO, String> emailCol;

    @FXML
    TableColumn<MemberDTO, String> phoneCol;

    @FXML
    TableColumn<MemberDTO, Integer> discountCol;

    @FXML
    TableColumn<MemberDTO, String> desCol;

    @FXML
    TableColumn<MemberDTO, Void> actionCol;

    @FXML
    public void initialize() {
        comCol.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("memberEmail"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("memberPhone"));
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        actionCol.setCellFactory(param -> new TableCell<>() {
            private final JFXButton editButton = new JFXButton("Edit");
            {
                editButton.setId("editBtn");
                editButton.setOnAction(event -> {
                    int member_id = getTableView().getItems().get(getIndex()).getMemberID().intValue();

                    try {
                        updateComController controller = new updateComController();
                        controller.setComId(member_id);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/updateComPage.fxml"));
                        loader.setController(controller);

                        Parent root = loader.load();
                        Stage window = (Stage) comTableView.getScene().getWindow();
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
                    setGraphic(editButton);
                }
            }
        });

        comTableView.setItems(comAccDB.getAllComAccount());
    }

    @FXML
    private void showProposalPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/staffProposalPage.fxml"));
            Stage window = (Stage) comTableView.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/loginPage.fxml"));
            Stage window = (Stage) comTableView.getScene().getWindow();
            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
