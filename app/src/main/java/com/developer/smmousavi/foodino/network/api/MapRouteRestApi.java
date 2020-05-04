package com.developer.smmousavi.foodino.network.api;

import com.developer.smmousavi.foodino.constants.Constants;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MapRouteRestApi {

    @GET("/routes/route/v1/driving/{origin_lng},{origin_lat};{dest_lng},{dest_lat}")
    @Headers("x-api-key: " + Constants.MAP_API_KEY)
    Call<JsonObject> getRoute(@Path("origin_lng") String originLng, @Path("origin_lat") String originLat,
                              @Path("dest_lng") String destLng, @Path("dest_lat") String destLat);

}
