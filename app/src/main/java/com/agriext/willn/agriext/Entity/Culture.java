package com.agriext.willn.agriext.Entity;

import android.graphics.drawable.Drawable;

public class Culture {
    private String name;
    private Drawable image;
    private double kc;

    public Culture(String name, Drawable image, double kc) {
        this.name = name;
        this.image = image;
        this.kc = kc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public double getKc() {
        return kc;
    }

    public void setKc(double kc) {
        this.kc = kc;
    }
}
