package com.developer.smmousavi.foodino.util;

public class Calculator {

    public static double getDiscountAmount(double price, Double discountPercent) {
        if (discountPercent != null) {
            double discountRate = discountPercent / 100;
            return price * discountRate;
        } else
            return 0;
    }

    public static double getFinalPrice(double price, Double discountPercent) {
        if (discountPercent != null)
            return price - getDiscountAmount(price, discountPercent);
        else
            return price;
    }
}
