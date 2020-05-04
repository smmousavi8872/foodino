package com.developer.smmousavi.foodino.repositories;

import com.developer.smmousavi.foodino.network.clients.MapRouteApiClient;

import androidx.lifecycle.MutableLiveData;

public class MapRouteRepository {

    public static MapRouteRepository sInstance;

    private MapRouteApiClient mMapRouteApiClient;

    public MutableLiveData<String> getRouteLd() {
        return mMapRouteApiClient.getRecipeLd();
    }

    public MutableLiveData<Boolean> getRouteQueryFailedLd() {
        return mMapRouteApiClient.getRouteRequestFailedLd();
    }

    private MapRouteRepository() {
        mMapRouteApiClient = MapRouteApiClient.getInstance();
    }

    public static MapRouteRepository getInstance() {
        if (sInstance == null) {
            sInstance = new MapRouteRepository();
            return sInstance;
        }
        return sInstance;
    }

    public void getRoute(double[] origin, double[] dest) {
        mMapRouteApiClient.getRouteApi(origin, dest);
    }

}
