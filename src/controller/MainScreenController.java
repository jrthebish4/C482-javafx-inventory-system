package controller;

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

public class MainScreenController implements Initializable {

    @FXML public Button exitButton;
    @FXML public AnchorPane mainScene;
    @FXML public Label appTitle;
    @FXML public SplitPane inventoryPane;
    @FXML public AnchorPane partsPane;
    @FXML public Label partTitle;
    @FXML public Button partSearchButton;
    @FXML public TextField partSearchBox;
    @FXML public TableView<Part> partTable;
    @FXML public TableColumn<Part, Integer> partIdColumn;
    @FXML public TableColumn<Part, String> partNameColumn;
    @FXML public TableColumn<Part, Integer> partInventoryColumn;
    @FXML public TableColumn<Part, Double> partPriceColumn;
    @FXML public Button addPartButton;
    @FXML public Button modifyPartButton;
    @FXML public Button deletePartButton;
    @FXML public AnchorPane productsPane;
    @FXML public Label productTitle;
    @FXML public Button productSearchButton;
    @FXML public TextField productSearchBox;
    @FXML public TableView<Product> productTable;
    @FXML public TableColumn<Product, Integer> productIdColumn;
    @FXML public TableColumn<Product, String> productNameColumn;
    @FXML public TableColumn<Product, Integer> productInventoryColumn;
    @FXML public TableColumn<Product, Double> productPriceColumn;
    @FXML public Button addProductButton;
    @FXML public Button modifyProductButton;
    @FXML public Button deleteProductButton;

    static Part partToModify;
    static Product productToModify;

    static int partToModifyIndex;
    static int productToModifyIndex;

    public void clickPartSearch() {
        partTable.setItems(Inventory.lookupPart(partSearchBox.getText()));
    }

    public void clickAddPart(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "/view/addPartScreen.fxml");
    }

    public void clickModifyPart(ActionEvent actionEvent) throws IOException {
        partToModify = partTable.getSelectionModel().getSelectedItem();
        partToModifyIndex = Inventory.getAllParts().indexOf(partToModify);
        if (partToModify == null) {
            Alert noSourceAlert = new Alert(Alert.AlertType.WARNING);
            noSourceAlert.setHeaderText("Part selection is required!");
            noSourceAlert.setContentText("Please select a part from the table before modifying.");
            noSourceAlert.showAndWait();
        } else {
            changeScene(actionEvent, "/view/modifyPartScreen.fxml");
            System.out.println("Part to modify: " + partToModify.toString());
        }
    }

    public void clickDeletePart() {
        Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
        partTable.setItems(Inventory.getAllParts());
        System.out.println("Updated Part Inventory: " + Inventory.getAllParts().toString());
    }

    public void clickProductSearch() {
        productTable.setItems(Inventory.lookupProduct(productSearchBox.getText()));
    }

    public void clickAddProduct(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "/view/addProductScreen.fxml");
    }

    public void clickModifyProduct(ActionEvent actionEvent) throws IOException {
        productToModify = productTable.getSelectionModel().getSelectedItem();
        productToModifyIndex = Inventory.getAllProducts().indexOf(productToModify);
        if (productToModify == null) {
            Alert noSourceAlert = new Alert(Alert.AlertType.WARNING);
            noSourceAlert.setHeaderText("Product selection is required.");
            noSourceAlert.setContentText("Please select a product from the table before modifying.");
            noSourceAlert.showAndWait();
        } else {
            changeScene(actionEvent, "/view/modifyProductScreen.fxml");
            System.out.println("Product to modify: " + productToModify.toString());
        }
    }

    public void clickDeleteProduct() {
        Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
        productTable.setItems(Inventory.getAllProducts());
        System.out.println("Updated Product Inventory: " + Inventory.getAllProducts().toString());
    }

    public void clickExit() {
        System.out.println("Closing application...");
        System.exit(0);
    }

    private void changeScene(ActionEvent actionEvent, String pathToSceneFxml) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(pathToSceneFxml));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(parent));
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        partTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());
    }

}
