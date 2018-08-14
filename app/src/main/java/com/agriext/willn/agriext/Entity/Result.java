package com.agriext.willn.agriext.Entity;

public class Result {
    private String id;
    private Culture culture;
    private double quantityWater;

    public Result(Culture culture, double quantityWater) {
        this.culture = culture;
        this.quantityWater = quantityWater;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Culture getCulture() {
        return culture;
    }

    public void setCulture(Culture culture) {
        this.culture = culture;
    }

    public double getQuantityWater() {
        return quantityWater;
    }

    public void setQuantityWater(double quantityWater) {
        this.quantityWater = quantityWater;
    }
}
