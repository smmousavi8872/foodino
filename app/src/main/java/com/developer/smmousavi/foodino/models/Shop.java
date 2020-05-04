package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Shop {

    public abstract String id();

    public abstract User buyer();

    public abstract List<Product> shopList();

    public abstract long date();

    public abstract double totalPrice();

    public abstract String state();

    public abstract List<Shop> history();

    public static Shop create(String id,
                              User buyer,
                              List<Product> shopList,
                              long date,
                              double totalPrice,
                              String state,
                              List<Shop> history) {

        return new AutoValue_Shop(id, buyer, shopList, date, totalPrice, state, history);

    }

}
