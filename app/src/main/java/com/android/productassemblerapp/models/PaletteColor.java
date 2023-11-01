package com.android.productassemblerapp.models;

import java.util.ArrayList;
import java.util.List;

public class PaletteColor {
    private String color;
    private String name;

    public PaletteColor(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<PaletteColor> getColors() {
        List<PaletteColor> colors = new ArrayList<>();
        colors.add(new PaletteColor("Red", "#FF0000"));
        colors.add(new PaletteColor("Green", "#00FF00"));
        colors.add(new PaletteColor("Blue", "#0000FF"));
        colors.add(new PaletteColor("Yellow", "#FFFF00"));
        return colors;
    }
}
