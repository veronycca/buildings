package com.veronika.buildings.model;

public class Building {

    private String owner;
    private AddressEntity address;
    private double value;
    private PropertyType type;
    private int size;

    public Building() {
    }

    public Building(String owner, AddressEntity address, double value, PropertyType type, int size) {
        this.owner = owner;
        this.address = address;
        this.value = value;
        this.type = type;
        this.size = size;
    }

    public String getOwner() {
        return owner;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public double getValue() {
        return value;
    }

    public PropertyType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}
