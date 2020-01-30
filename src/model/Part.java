package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Part {
    private static final AtomicInteger count = new AtomicInteger(0);

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty stock;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;

    public Part(String name, double price, int stock, int min, int max) {
        this.id = new SimpleIntegerProperty(count.getAndIncrement());
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(Double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock = new SimpleIntegerProperty(stock);
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(int min) {
        this.min = new SimpleIntegerProperty(min);
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max = new SimpleIntegerProperty(max);
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", stock=" + getStock() +
                ", min=" + getMin() +
                ", max=" + getMax() +
                ',';
    }

}
