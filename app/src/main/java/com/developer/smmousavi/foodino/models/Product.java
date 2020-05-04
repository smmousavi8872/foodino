package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

import java.util.List;

import io.reactivex.annotations.Nullable;

@AutoValue
public abstract class Product {

    public abstract String id();

    public abstract String title();

    @Nullable
    public abstract String titleDescription();

    @Nullable
    public abstract String avatarUrl();

    @Nullable
    public abstract String description();

    @Nullable
    public abstract String guaranteeName();

    public abstract String sellerName();

    public abstract int maxBuyCount();

    public abstract Specifications specificaitions();

    public abstract List<Category> parentCategoryList();

    public abstract List<String> imageUrlList();

    public abstract double price();

    @Nullable
    public abstract Double discountPercent();

    public abstract long date();

    public abstract boolean isAvailable();

    public abstract boolean isSpecialOffer();

    @Nullable
    public abstract List<Product> relatedProductList();

    public abstract long totalSellCount();

    @Nullable
    public abstract String warning();

    public static Product create(String id,
                                 String title,
                                 String titleDescription,
                                 String avatarUrl,
                                 String description,
                                 String guaranteeName,
                                 String sellerName,
                                 int maxBuyCount,
                                 Specifications specificaitions,
                                 List<Category> parentCategoryList,
                                 List<String> imageUrlList,
                                 double price,
                                 Double discountPercent,
                                 long date,
                                 boolean isAvailable,
                                 boolean isSpecialOffer,
                                 List<Product> relatedProductList,
                                 long totalSellCount,
                                 String warning) {

        return new AutoValue_Product(id,
            title,
            titleDescription,
            avatarUrl,
            description,
            guaranteeName,
            sellerName,
            maxBuyCount,
            specificaitions,
            parentCategoryList,
            imageUrlList,
            price,
            discountPercent,
            date,
            isAvailable,
            isSpecialOffer,
            relatedProductList,
            totalSellCount,
            warning
        );
    }
}
