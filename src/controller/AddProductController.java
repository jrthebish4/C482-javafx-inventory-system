package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {


    @FXML public AnchorPane addProductPane;
    @FXML public Label addProductLabel;
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
    @FXML public TableView<Part> addPartTable;
    @FXML public TableColumn<Part, Integer> addPartIdColumn;
    @FXML public TableColumn<Part, String> addPartNameColumn;
    @FXML public TableColumn<Part, Integer> addPartInventoryColumn;
    @FXML public TableColumn<Part, Double> addPartPriceColumn;
    @FXML public Button addPartButton;
    @FXML public TableView<Part> deletePartTable;
    @FXML public TableColumn<Part, Integer> deletePartIdColumn;
    @FXML public TableColumn<Part, String> deletePartNameColumn;
    @FXML public TableColumn<Part, Integer> deletePartInventoryColumn;
    @FXML public TableColumn<Part, Double> deletePartPriceColumn;
    @FXML public Button deletePartButton;
    @FXML public Button saveButton;
    @FXML public Button cancelButton;
    private Product productToAdd = new Product();

    public void searchProducts(ActionEvent actionEvent) {
        String searchValue = searchField.getText();
        List<Part> parts = Inventory.getAllParts();
        ObservableList<Part> matchedParts = FXCollections.observableArrayList();
        for (Part part : parts) {
            if (part.getName().contains(searchValue)) {
                matchedParts.add(part);
            }
        }
        addPartTable.setItems(matchedParts);
        deletePartTable.setItems(matchedParts);
    }

    public void associatePart(ActionEvent actionEvent) {
        productToAdd.addAssociatedPart(
                addPartTable.getSelectionModel().getSelectedItem()
        );
    }

    public void dissociatePart(ActionEvent actionEvent) {
        productToAdd.deleteAssociatedPart(
                addPartTable.getSelectionModel().getSelectedItem()
        );
    }

    public void saveProduct(ActionEvent actionEvent) throws IOException {
        productToAdd.setName(new SimpleStringProperty(productNameField.getText()));
        productToAdd.setStock(new SimpleIntegerProperty(Integer.parseInt(inventoryField.getText())));
        productToAdd.setPrice(new SimpleDoubleProperty(Double.parseDouble(priceField.getText())));
        productToAdd.setMax(new SimpleIntegerProperty(Integer.parseInt(maxField.getText())));
        productToAdd.setMin(new SimpleIntegerProperty(Integer.parseInt(minField.getText())));
        Inventory.addProduct(productToAdd);

        System.out.print("Updated Product List: " + Inventory.getAllProducts().toString());

        returnToMainScene(actionEvent);
    }

    public void cancelProductCreation(ActionEvent actionEvent) throws IOException {
        returnToMainScene(actionEvent);
    }

    private void returnToMainScene(ActionEvent actionEvent) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(new Scene(mainScreenParent));
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartIdColumn.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
        addPartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        addPartPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        deletePartIdColumn.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
        deletePartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        deletePartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        deletePartPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        addPartTable.setItems(Inventory.getAllParts());
        deletePartTable.setItems(Inventory.getAllParts());
    }
}
