package model;

import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part {

    private SimpleIntegerProperty machineId;

    public InHouse(String name, double price, int stock, int min, int max, int machineId) {
        super(name, price, stock, min, max);
        this.machineId = new SimpleIntegerProperty(machineId);
    }

    public int getMachineId() {
        return machineId.get();
    }

    public void setMachineId(SimpleIntegerProperty machineId) {
        this.machineId = machineId;
    }

    @Override
    public String toString() {
        return super.toString() +
                " machineId=" + getMachineId() +
                '}';
    }

}
