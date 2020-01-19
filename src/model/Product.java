package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static final AtomicInteger count = new AtomicInteger(0);

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty stock;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;

    public Product(String name, double price, int stock, int min, int max) {
        this.id = new SimpleIntegerProperty(count.getAndIncrement());
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAsPart) {
        boolean successfulDelete = false;

        for (Part part : associatedParts) {
            if (part.getId() == selectedAsPart.getId()) {
                associatedParts.remove(selectedAsPart.getId());
                successfulDelete = true;
            }
        }

        return successfulDelete;
    }

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(SimpleDoubleProperty price) {
        this.price = price;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(SimpleIntegerProperty stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(SimpleIntegerProperty min) {
        this.min = min;
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(SimpleIntegerProperty max) {
        this.max = max;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "associatedParts=" + associatedParts +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
