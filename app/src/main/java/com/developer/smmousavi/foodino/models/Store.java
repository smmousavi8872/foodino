package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

import androidx.annotation.Nullable;

@AutoValue
public abstract class Store {

    public abstract String id();

    public abstract String name();

    public abstract String address();

    public abstract String owner();

    public abstract String validationCode();

    public abstract Product productToSell();

    @Nullable
    public abstract Double userSatisfaction();

    public abstract long voteCount();

    public static Store create(String id,
                        String name,
                        String address,
                        String owner,
                        String validationCode,
                        Product productToSell,
                        Double userStaisfaction,
                        long voteCount) {

        return new AutoValue_Store(id, name, address, owner, validationCode, productToSell, userStaisfaction, voteCount);
    }
}
