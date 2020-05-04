package com.developer.smmousavi.foodino.util;

import java.text.DecimalFormat;

public class PriceUtils {

    public static double getPriceWithDiscount(double mainPrice, double discountPercent) {
        return mainPrice - mainPrice * (discountPercent / 100.0);
    }

    public static String getFormattedPrice(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
}
