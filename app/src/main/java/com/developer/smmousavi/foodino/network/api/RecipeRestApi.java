package com.developer.smmousavi.foodino.network.api;

import com.developer.smmousavi.foodino.network.reciperesponses.ApiResponse;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeResponse;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeRestApi {

    //GET SPECIAL RECIPES RESPONSE
    @GET("api/search")
    LiveData<ApiResponse<RecipeSearchResponse>>  getSpecialRecipes(
        @Query("key") String key,
        @Query("q") String query,
        @Query("page") String page);


    //GET PREVIEW RECIPES RESPONSE
    @GET("api/search")
    LiveData<ApiResponse<RecipeSearchResponse>> getIncredibleRecipes(
        @Query("key") String key,
        @Query("q") String query,
        @Query("page") String page);


    //GET SUGGESTIONS RECIPES RESPONSE
    @GET("api/search")
    LiveData<ApiResponse<RecipeSearchResponse>> getSuggestionRecipes(
        @Query("key") String key,
        @Query("q") String query,
        @Query("page") String page);


    //GET RECIPE RESPONSE
    @GET("api/get")
    Call<RecipeResponse> getRecipe(
        @Query("key") String key,
        @Query("rId") String rId);
}
