package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    ObservableList<Part> allParts;
    ObservableList<Product> allProducts;

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) throws NullPointerException {
        Part partFound = null;

        for (Part part : allParts) {
            if (part.getId() == partId) partFound = part;
        }

        return partFound;
    }

    public Product lookupProduct(int productId) throws NullPointerException {
        Product productFound = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) productFound = product;
        }

        return productFound;
    }


    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) partsFound.add(part);
        }

        return partsFound;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) productsFound.add(product);
        }

        return productsFound;
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public boolean deletePart(Part selectedPart) {
        boolean successfulDelete = false;

        for (Part part : allParts) {
            if (part.getId() == selectedPart.getId()) {
                allParts.remove(part);
                successfulDelete = true;
            }
        }

        return successfulDelete;
    }

    public boolean deleteProduct(Product selectedProduct) {
        boolean successfulDelete = false;

        for (Product product : allProducts) {
            if (product.getId() == selectedProduct.getId()) {
                allProducts.remove(product);
                successfulDelete = true;
            }
        }

        return successfulDelete;
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}
