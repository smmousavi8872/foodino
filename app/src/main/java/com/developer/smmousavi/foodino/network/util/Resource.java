package com.developer.smmousavi.foodino.network.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    public enum Status {
        LOADING,
        ERROR,
        SUCCESS
    }

    @NonNull
    public final Status mStatus;
    @Nullable
    final T mData;
    @Nullable
    final String mMessage;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        mStatus = status;
        mData = data;
        mMessage = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}
