package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Color {

    public abstract String colorName();

    public abstract String colorCode();

    public static Color create(String colorName, String colorCode) {
        return new AutoValue_Color(colorName, colorCode);
    }
}
