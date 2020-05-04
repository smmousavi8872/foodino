package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {

    public abstract String userId();

    public abstract String username();

    public abstract String email();

    public abstract String password();

    public abstract long birthDate();

    public abstract long subscirbeDate();

    public abstract Shop shop();


    public static User create(String userId,
                              String username,
                              String email,
                              String password,
                              long birthDate,
                              long subscirbeDate, Shop shop) {
        return new AutoValue_User(userId, username, email, password, birthDate, subscirbeDate, shop);
    }


}
