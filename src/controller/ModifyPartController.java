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

public class ModifyPartController implements Initializable {

    @FXML public AnchorPane modifyPartPane;
    @FXML public Label modifyPartLabel;
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

    final private Part partToModify = MainScreenController.partToModify;

    public void displayInHousePartFields() {
        companyLabel.setText("Machine ID");
        companyField.setPromptText("Machine ID");
    }

    public void displayOutsourcedPartFields() {
        companyLabel.setText("Company");
        companyField.setPromptText("Company Name");
    }

    public void modifyNewPart(ActionEvent actionEvent) throws IOException {
        if (requiredFieldsCheck()) {
            if (sourceToggleGroup.getSelectedToggle().equals(inHouseRadio)) {
                InHouse updatedPart = convertInputsToInHousePart();
                Inventory.updatePart(
                        MainScreenController.partToModifyIndex,
                        updatedPart
                );
            } else if (sourceToggleGroup.getSelectedToggle().equals(outsourcedRadio)) {
                Outsourced updatedPart = convertInputsToOutsourcedPart();
                Inventory.updatePart(
                        MainScreenController.partToModifyIndex,
                        updatedPart
                );
            }
            System.out.print(Inventory.getAllParts().toString());
            returnToMainScene(actionEvent);
        }
    }

    public void cancelModifyPart(ActionEvent actionEvent) throws IOException {
        returnToMainScene(actionEvent);
    }

    private InHouse convertInputsToInHousePart() {
        InHouse modifiedPart = new InHouse(partNameField.getText(), Double.parseDouble(priceField.getText()),
                Integer.parseInt(inventoryField.getText()), Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText()), Integer.parseInt(companyField.getText()));

        modifiedPart.setId(partToModify.getId());
        return modifiedPart;
    }

    private Outsourced convertInputsToOutsourcedPart() {
        Outsourced modifiedPart = new Outsourced(partNameField.getText(), Double.parseDouble(priceField.getText()),
                Integer.parseInt(inventoryField.getText()), Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText()), companyField.getText());

        modifiedPart.setId(partToModify.getId());
        return modifiedPart;
    }

    private boolean requiredFieldsCheck() {
        if (partNameField.getText().isEmpty() || inventoryField.getText().isEmpty() ||
                priceField.getText().isEmpty() || maxField.getText().isEmpty() ||
                minField.getText().isEmpty() || companyField.getText().isEmpty()) {

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

        partIdField.setText(Integer.toString(partToModify.getId()));
        partNameField.setText(partToModify.getName());
        inventoryField.setText(Integer.toString(partToModify.getStock()));
        priceField.setText(Double.toString(partToModify.getPrice()));
        maxField.setText(Integer.toString(partToModify.getMax()));
        minField.setText(Integer.toString(partToModify.getMin()));

        if (partToModify instanceof InHouse) {
            companyLabel.setText("Machine ID");
            sourceToggleGroup.selectToggle(inHouseRadio);
            companyField.setText(Integer.toString(((InHouse) partToModify).getMachineId()));
        } else if (partToModify instanceof Outsourced) {
            companyLabel.setText("Company");
            sourceToggleGroup.selectToggle(outsourcedRadio);
            companyField.setText(((Outsourced) partToModify).getCompanyName());
        }
    }

}
