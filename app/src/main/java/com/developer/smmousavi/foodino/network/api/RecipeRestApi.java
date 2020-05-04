package com.developer.smmousavi.foodino.network.api;

import com.developer.smmousavi.foodino.network.reciperesponses.RecipeResponse;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeRestApi {

    //GET SPECIAL RECIPES RESPONSE
    @GET("api/search")
    Call<RecipeSearchResponse> getSpecialRecipes(
        @Query("key") String key,
        @Query("q") String query,
        @Query("page") String page);


    //GET PREVIEW RECIPES RESPONSE
    @GET("api/search")
    Call<RecipeSearchResponse> getPreviewRecipes(
        @Query("key") String key,
        @Query("q") String query,
        @Query("page") String page);


    //GET SUGGESTIONS RECIPES RESPONSE
    @GET("api/search")
    Call<RecipeSearchResponse> getSuggestionRecipes(
        @Query("key") String key,
        @Query("q") String query,
        @Query("page") String page);


    //GET RECEPIE RESPONSE
    @GET("api/get")
    Call<RecipeResponse> getRecipe(
        @Query("key") String key,
        @Query("rId") String rId);
}
