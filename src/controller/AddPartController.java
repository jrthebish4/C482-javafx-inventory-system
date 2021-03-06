package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML public AnchorPane addPartPane;
    @FXML public Label addPartLabel;
    @FXML public RadioButton inHouseRadio;
    @FXML public RadioButton outsourcedRadio;
    @FXML public Label partIdLabel;
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
    private ToggleGroup sourceToggleGroup;

    public void addNewPart(ActionEvent actionEvent) throws IOException {
        if (requiredFieldsCheck()) {
            if (sourceToggleGroup.getSelectedToggle().equals(inHouseRadio)) {
                Inventory.addPart(
                        convertInputsToInHousePart()
                );
            } else if (sourceToggleGroup.getSelectedToggle().equals(outsourcedRadio)) {
                Inventory.addPart(
                        convertInputsToOutsourcedPart()
                );
            }
            System.out.println("Updated Part List: " + Inventory.getAllParts().toString());
            returnToMainScene(actionEvent);
        }
    }

    public void cancelAddPart(ActionEvent actionEvent) throws IOException {
        returnToMainScene(actionEvent);
    }

    public void displayInHousePartFields() {
        companyLabel.setText("Machine ID");
        companyField.setPromptText("Machine ID");
    }

    public void displayOutsourcedPartFields() {
        companyLabel.setText("Company");
        companyField.setPromptText("Company Name");
    }

    private Part convertInputsToInHousePart() {
        return new InHouse(partNameField.getText(), Double.parseDouble(priceField.getText()),
                Integer.parseInt(inventoryField.getText()), Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText()), Integer.parseInt(companyField.getText()));
    }

    private Part convertInputsToOutsourcedPart() {
        return new Outsourced(partNameField.getText(), Double.parseDouble(priceField.getText()),
                Integer.parseInt(inventoryField.getText()), Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText()), companyField.getText());
    }

    private boolean requiredFieldsCheck() {
        if (partNameField.getText().isEmpty() || inventoryField.getText().isEmpty() ||
                priceField.getText().isEmpty() || maxField.getText().isEmpty() ||
                minField.getText().isEmpty() || companyField.getText().isEmpty() ||
                sourceToggleGroup.getSelectedToggle() == null) {

            Alert noSourceAlert = new Alert(Alert.AlertType.WARNING);
            noSourceAlert.setHeaderText("Missing Required Fields.");
            noSourceAlert.setContentText("All fields are required. Please enter all fields to continue.");
            noSourceAlert.showAndWait();

            return false;
        }

        return true;
    }

    private void returnToMainScene(ActionEvent actionEvent) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(mainScreenParent));
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sourceToggleGroup = new ToggleGroup();
        inHouseRadio.setToggleGroup(sourceToggleGroup);
        outsourcedRadio.setToggleGroup(sourceToggleGroup);
    }

}
