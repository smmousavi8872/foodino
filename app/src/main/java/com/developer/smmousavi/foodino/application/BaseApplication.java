package com.developer.smmousavi.foodino.application;


import android.content.Context;

import com.developer.smmousavi.foodino.application.di.DaggerApplicationComponent;
import com.developer.smmousavi.foodino.constants.Constants;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ir.map.sdk_map.Mapir;

public class BaseApplication extends DaggerApplication {

    private static volatile Context sApplicationContext;

    public static Context getAppContext() {
        return sApplicationContext;
    }

    @Override
    public void onCreate() {
        try {
            sApplicationContext = getApplicationContext();
        } catch (Throwable ignore) {

        }
        super.onCreate();
        if (sApplicationContext == null) {
            sApplicationContext = getApplicationContext();
        }
        initMap();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }

    private void initMap() {
        Mapir.getInstance(this, Constants.MAP_API_KEY);
    }

}
