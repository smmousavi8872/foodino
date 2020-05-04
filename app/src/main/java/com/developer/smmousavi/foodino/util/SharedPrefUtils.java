package com.developer.smmousavi.foodino.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.developer.smmousavi.foodino.application.BaseApplication;

public class SharedPrefUtils {

    public static final String SHARED_PERF_NAME = "sharedPreferencesName";
    public static final String SHOPPING_BASKET_BADGE_COUNT = "shoppingBasketBadgeCount";

    private static SharedPreferences mSharedPref = getSharedPref();

    private static SharedPreferences getSharedPref() {
        return BaseApplication.getAppContext()
            .getSharedPreferences(SHARED_PERF_NAME, Context.MODE_PRIVATE);
    }

    public static void putInt(String key, int value) {
        mSharedPref.edit()
            .putInt(key, value)
            .apply();
    }

    public static int getInt(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void putString(String key, String value) {
        mSharedPref.edit()
            .putString(key, value)
            .apply();
    }

    public static String getString(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }
}
