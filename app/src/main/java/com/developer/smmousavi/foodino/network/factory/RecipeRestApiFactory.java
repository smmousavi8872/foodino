package com.developer.smmousavi.foodino.network.factory;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.network.api.RecipeRestApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRestApiFactory {

    private RecipeRestApiFactory() {
    }

    public static RecipeRestApi create() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.RECIPE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build();
        return retrofit.create(RecipeRestApi.class);
    }

}
