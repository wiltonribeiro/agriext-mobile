package com.agriext.willn.agriext.Entity;

public class Culture {
    private String name;
    private String urlImage;
    private double kc;

    public Culture(String name, String urlImage, double kc) {
        this.name = name;
        this.urlImage = urlImage;
        this.kc = kc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public double getKc() {
        return kc;
    }

    public void setKc(double kc) {
        this.kc = kc;
    }
}
