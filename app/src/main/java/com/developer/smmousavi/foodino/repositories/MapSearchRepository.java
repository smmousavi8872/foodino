package com.developer.smmousavi.foodino.repositories;

import com.developer.smmousavi.foodino.network.clients.MapSearchClient;
import com.developer.smmousavi.foodino.network.mapresponse.MapSearchBody;
import com.developer.smmousavi.foodino.network.mapresponse.Value;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class MapSearchRepository {

    public static MapSearchRepository sInstance;

    private MapSearchClient mMapSearchApiClient;

    public MutableLiveData<List<Value>> getMapSearchLd() {
        return mMapSearchApiClient.getMapSearchLd();
    }

    public MutableLiveData<Boolean> getMapSearchQueryFailedLd() {
        return mMapSearchApiClient.getMapSearchRequestFailedLd();
    }

    private MapSearchRepository() {
        mMapSearchApiClient = MapSearchClient.getInstance();
    }

    public static MapSearchRepository getInstance() {
        if (sInstance == null) {
            sInstance = new MapSearchRepository();
            return sInstance;
        }
        return sInstance;
    }

    public void searchMap(MapSearchBody body) {
        mMapSearchApiClient.getMapSearchApi(body);
    }

}
