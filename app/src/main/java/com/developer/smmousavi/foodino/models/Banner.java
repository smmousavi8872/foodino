package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Banner {

    public abstract String id();

    public abstract String url();

    public abstract String title();

    public static Banner create(String id,
                                String url,
                                String title) {

        return new AutoValue_Banner(id, url, title);
    }
}
