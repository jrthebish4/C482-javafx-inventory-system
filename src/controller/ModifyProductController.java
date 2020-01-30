package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    @FXML public AnchorPane modifyProductPane;
    @FXML public Label modifyProductLabel;
    @FXML public Label idLabel;
    @FXML public TextField productIdField;
    @FXML public Label nameLabel;
    @FXML public TextField productNameField;
    @FXML public Label invetoryLabel;
    @FXML public TextField inventoryField;
    @FXML public Label priceLabel;
    @FXML public TextField priceField;
    @FXML public Label maxLabel;
    @FXML public TextField maxField;
    @FXML public Label minLabel;
    @FXML public TextField minField;
    @FXML public Button searchButton;
    @FXML public TextField searchField;
    @FXML public TableView<Part> modifyPartTable;
    @FXML public TableColumn<Part, Integer> modifyPartIdColumn;
    @FXML public TableColumn<Part, String> modifyPartNameColumn;
    @FXML public TableColumn<Part, Integer> modifyPartInventoryColumn;
    @FXML public TableColumn<Part, Double> modifyPartPriceColumn;
    @FXML public Button modifyPartButton;
    @FXML public TableView<Part> deletePartTable;
    @FXML public TableColumn<Part, Integer> deletePartIdColumn;
    @FXML public TableColumn<Part, String> deletePartNameColumn;
    @FXML public TableColumn<Part, Integer> deletePartInventoryColumn;
    @FXML public TableColumn<Part, Double> deletePartPriceColumn;
    @FXML public Button deletePartButton;
    @FXML public Button saveButton;
    @FXML public Button cancelButton;

    final private Product productToModify = MainScreenController.productToModify;

    public void searchParts() {
        modifyPartTable.setItems(Inventory.lookupPart(searchField.getText()));
    }

    public void associatePart() {
        productToModify.addAssociatedPart(
                modifyPartTable.getSelectionModel().getSelectedItem()
        );
        deletePartTable.setItems(productToModify.getAllAssociatedParts());
    }

    public void dissociatePart() {
        productToModify.deleteAssociatedPart(
                modifyPartTable.getSelectionModel().getSelectedItem()
        );
        deletePartTable.setItems(productToModify.getAllAssociatedParts());
    }

    public void saveProduct(ActionEvent actionEvent) throws IOException {
        if (requiredFieldsCheck() && requiredPartCheck()) {
            productToModify.setName(new SimpleStringProperty(productNameField.getText()));
            productToModify.setStock(new SimpleIntegerProperty(Integer.parseInt(inventoryField.getText())));
            productToModify.setPrice(new SimpleDoubleProperty(Double.parseDouble(priceField.getText())));
            productToModify.setMax(new SimpleIntegerProperty(Integer.parseInt(maxField.getText())));
            productToModify.setMin(new SimpleIntegerProperty(Integer.parseInt(minField.getText())));
            Inventory.updateProduct(MainScreenController.productToModifyIndex, productToModify);

            System.out.println("Updated Product List: " + Inventory.getAllProducts().toString());
            returnToMainScene(actionEvent);
        }
    }

    public void cancelProductModify(ActionEvent actionEvent) throws IOException {
        returnToMainScene(actionEvent);
    }

    private boolean requiredFieldsCheck() {
        if (productNameField.getText().isEmpty() || inventoryField.getText().isEmpty() ||
                priceField.getText().isEmpty() || maxField.getText().isEmpty() ||
                minField.getText().isEmpty()) {

            Alert noSourceAlert = new Alert(Alert.AlertType.WARNING);
            noSourceAlert.setHeaderText("Missing Required Fields.");
            noSourceAlert.setContentText("All fields are required. Please enter all fields to continue.");
            noSourceAlert.showAndWait();

            return false;
        }

        return true;
    }

    private boolean requiredPartCheck() {
        if (productToModify.getAllAssociatedParts().size() <= 0) {

            Alert noSourceAlert = new Alert(Alert.AlertType.WARNING);
            noSourceAlert.setHeaderText("Missing Required Parts.");
            noSourceAlert.setContentText("A product requires at least one part. " +
                    "Please associate at least one part to continue.");
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
        productIdField.setText(Integer.toString(productToModify.getId()));
        productNameField.setText(productToModify.getName());
        inventoryField.setText(Integer.toString(productToModify.getStock()));
        priceField.setText(Double.toString(productToModify.getPrice()));
        maxField.setText(Integer.toString(productToModify.getMax()));
        minField.setText(Integer.toString(productToModify.getMin()));

        modifyPartIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        modifyPartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modifyPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        modifyPartPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        deletePartIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        deletePartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        deletePartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        deletePartPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        modifyPartTable.setItems(Inventory.getAllParts());
        deletePartTable.setItems(productToModify.getAllAssociatedParts());
    }

}
