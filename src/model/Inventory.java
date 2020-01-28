package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) throws NullPointerException {
        Part partFound = null;

        for (Part part : allParts) {
            if (part.getId() == partId) partFound = part;
        }

        return partFound;
    }

    public static Product lookupProduct(int productId) throws NullPointerException {
        Product productFound = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) productFound = product;
        }

        return productFound;
    }


    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) partsFound.add(part);
        }

        return partsFound;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) productsFound.add(product);
        }

        return productsFound;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        boolean successfulDelete = false;

        if (!allParts.isEmpty()) {
            for (Part part : allParts) {
                if (part.getId() == selectedPart.getId()) {
                    allParts.remove(part);
                    successfulDelete = true;
                }
            }
        }

        return successfulDelete;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        boolean successfulDelete = false;

        if (!allProducts.isEmpty()) {
            for (Product product : allProducts) {
                if (product.getId() == selectedProduct.getId()) {
                    allProducts.remove(product);
                    successfulDelete = true;
                }
            }
        }

        return successfulDelete;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
