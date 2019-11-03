package com.veronika.buildings.model;

public enum PropertyType {
    APARTMENT(0.01),
    HOUSE(0.02),
    INDUSTRIAL(0.03);

    private double taxRate;

    PropertyType(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
