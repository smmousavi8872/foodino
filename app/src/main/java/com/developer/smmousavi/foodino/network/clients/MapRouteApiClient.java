package com.developer.smmousavi.foodino.network.clients;

import android.util.Log;

import com.developer.smmousavi.foodino.network.factory.MapRouteRestApiFactory;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapRouteApiClient {

    private static MapRouteApiClient sInstance;

    private MutableLiveData<String> mRouteLD;
    private MutableLiveData<Boolean> mRouteRequestFailed;
    // private GetRecipesRunnable mGetRecipeRunnable;

    public static MapRouteApiClient getInstance() {
        if (sInstance == null)
            sInstance = new MapRouteApiClient();
        return sInstance;
    }

    private MapRouteApiClient() {
        mRouteLD = new MutableLiveData<>();
        mRouteRequestFailed = new MutableLiveData<>();
    }

    public MutableLiveData<String> getRecipeLd() {
        return mRouteLD;
    }

    public MutableLiveData<Boolean> getRouteRequestFailedLd() {
        return mRouteRequestFailed;
    }


    public void getRouteApi(double[] origin, double[] dest) {
        MapRouteRestApiFactory.create()
            .getRoute(String.valueOf(origin[0]), String.valueOf(origin[1]), String.valueOf(dest[0]), String.valueOf(dest[1]))
            .enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.i("REQUEST_CODE", "code: " + response.code());
                    if (response.code() == 200) {
                        mRouteLD.postValue(getGeometryObject(response.body()));
                        mRouteRequestFailed.postValue(false);
                    } else {
                        mRouteLD.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    mRouteRequestFailed.postValue(true);
                }
            });
    }

    String getGeometryObject(JsonObject responseBody) {
        try {
            JSONObject bodyJson = new JSONObject(responseBody.toString());
            return bodyJson.getJSONArray("routes").getJSONObject(0).get("geometry").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
