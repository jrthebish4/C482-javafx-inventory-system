package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    @FXML public AnchorPane addPartPane;
    @FXML public Label addPartLabel;
    @FXML public RadioButton inHouseRadio;
    @FXML public Label partIdLabel;
    @FXML public RadioButton outsourcedRadio;
    @FXML public TextField partIdField;
    @FXML public Label partNameLabel;
    @FXML public TextField partNameField;
    @FXML public Label inventoryLabel;
    @FXML public TextField inventoryField;
    @FXML public Label priceLabel;
    @FXML public TextField priceField;
    @FXML public Label maxLabel;
    @FXML public TextField maxField;
    @FXML public Label minLabel;
    @FXML public TextField minField;
    @FXML public Label companyLabel;
    @FXML public TextField companyField;
    @FXML public Button saveButton;
    @FXML public Button cancelButton;

    @FXML
    public void displayInHousePartFields(ActionEvent actionEvent) {
    }

    @FXML
    public void displayOutsourcedPartFields(ActionEvent actionEvent) {
    }

    @FXML
    public void addNewPart(ActionEvent actionEvent) {

    }

    @FXML
    public void cancelAddPart(ActionEvent actionEvent) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(new Scene(mainScreenParent));
        window.show();
    }
}
