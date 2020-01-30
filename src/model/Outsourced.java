package model;

import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {

    private SimpleStringProperty companyName;

    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name, price, stock, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName = new SimpleStringProperty(companyName);
    }

    @Override
    public String toString() {
        return super.toString() +
                " companyName='" + getCompanyName() + '\'' +
                '}';
    }

}
