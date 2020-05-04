package com.developer.smmousavi.foodino.network.api;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.network.mapresponse.MapSearchBody;
import com.developer.smmousavi.foodino.network.mapresponse.MapSearchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MapSearchRestApi {

    @POST("/search/v2")
    @Headers({"x-api-key: " + Constants.MAP_API_KEY, "Content-Type: " + "application/json"})
    Call<MapSearchResponse> searchMap(@Body MapSearchBody body);
}
