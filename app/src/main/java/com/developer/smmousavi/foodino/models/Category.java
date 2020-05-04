package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

import java.io.Serializable;
import java.util.List;

import io.reactivex.annotations.Nullable;


@AutoValue
public abstract class Category implements Serializable {

    public abstract String id();

    public abstract String title();

    @Nullable
    public abstract String iconUrl();

    public abstract boolean isAbstract();

    @Nullable
    public abstract List<Category> abstractSubcategoryList();

    @Nullable
    public abstract List<Product> concreteSubcategoryList();

    public static Category create(String id,
                                  String title,
                                  String iconUrl,
                                  boolean isAbstract,
                                  List<Category> abstractSubcategoryList,
                                  List<Product> concreteSubcategoryList) {

        return new AutoValue_Category(id, title, iconUrl, isAbstract, abstractSubcategoryList, concreteSubcategoryList);
    }
}
