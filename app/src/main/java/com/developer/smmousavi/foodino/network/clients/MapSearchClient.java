package com.developer.smmousavi.foodino.network.clients;

import android.util.Log;

import com.developer.smmousavi.foodino.network.factory.MapSearchRestApiFactory;
import com.developer.smmousavi.foodino.network.mapresponse.MapSearchBody;
import com.developer.smmousavi.foodino.network.mapresponse.MapSearchResponse;
import com.developer.smmousavi.foodino.network.mapresponse.Value;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapSearchClient {

    private static MapSearchClient sInstance;
    private MutableLiveData<List<Value>> mMapSearchLd;
    private MutableLiveData<Boolean> mMapSearchRequestFailed;
    // private GetRecipesRunnable mGetRecipeRunnable;


    public static MapSearchClient getInstance() {
        if (sInstance == null)
            sInstance = new MapSearchClient();
        return sInstance;
    }

    private MapSearchClient() {
        mMapSearchLd = new MutableLiveData<>();
        mMapSearchRequestFailed = new MutableLiveData<>();
    }

    public MutableLiveData<List<Value>> getMapSearchLd() {
        return mMapSearchLd;
    }

    public MutableLiveData<Boolean> getMapSearchRequestFailedLd() {
        return mMapSearchRequestFailed;
    }


    public void getMapSearchApi(MapSearchBody body) {
        MapSearchRestApiFactory.create().searchMap(body)
            .enqueue(new Callback<MapSearchResponse>() {
                @Override
                public void onResponse(Call<MapSearchResponse> call, Response<MapSearchResponse> response) {
                    Log.i("REQUEST_CODE", "code: " + response.code());
                    if (response.code() == 200) {
                        mMapSearchLd.postValue(response.body().getValueList());
                    } else {
                        mMapSearchLd.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<MapSearchResponse> call, Throwable t) {
                    mMapSearchRequestFailed.postValue(true);
                }
            });
    }


}
