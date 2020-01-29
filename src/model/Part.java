package model;

import javafx.beans.property.*;

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
