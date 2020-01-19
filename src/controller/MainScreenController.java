package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    @FXML public Button exitButton;
    @FXML public AnchorPane mainScene;
    @FXML public Label appTitle;
    @FXML public SplitPane inventoryPane;
    @FXML public AnchorPane partsPane;
    @FXML public Label partTitle;
    @FXML public Button partSearchButton;
    @FXML public TextField partSearchBox;
    @FXML public TableView partTable;
    @FXML public TableColumn partIdColumn;
    @FXML public TableColumn partNameColumn;
    @FXML public TableColumn partInventoryColumn;
    @FXML public TableColumn partPriceColumn;
    @FXML public Button addPartButton;
    @FXML public Button modifyPartButton;
    @FXML public Button deletePartButton;
    @FXML public AnchorPane productsPane;
    @FXML public Label productTitle;
    @FXML public Button productSearchButton;
    @FXML public TextField productSearchBox;
    @FXML public TableView productTable;
    @FXML public TableColumn productIdColumn;
    @FXML public TableColumn productNameColumn;
    @FXML public TableColumn productInventoryColumn;
    @FXML public TableColumn productPriceColumn;
    @FXML public Button addProductButton;
    @FXML public Button modifyProductButton;
    @FXML public Button deleteProductButton;

    @FXML
    public void clickPartSearch(ActionEvent actionEvent) {

    }

    @FXML
    public void partTextSearch(ActionEvent actionEvent) {

    }

    @FXML
    public void clickAddPart(ActionEvent actionEvent) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/addPartScreen.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(new Scene(addPartParent));
        window.show();
    }

    @FXML
    public void clickModifyPart(ActionEvent actionEvent) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/modifyPartScreen.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(new Scene(addPartParent));
        window.show();
    }

    @FXML
    public void clickDeletePart(ActionEvent actionEvent) {

    }

    @FXML
    public void clickProductSearch(ActionEvent actionEvent) {

    }

    @FXML
    public void productTextSeach(ActionEvent actionEvent) {

    }

    @FXML
    public void clickAddProduct(ActionEvent actionEvent) throws IOException {
    }

    @FXML
    public void clickModifyProduct(ActionEvent actionEvent) {

    }

    @FXML
    public void clickDeleteProduct(ActionEvent actionEvent) {

    }

    @FXML
    public void clickExit(ActionEvent actionEvent) {
        System.exit(0);
    }

}
