package com.developer.smmousavi.foodino.network.reciperesponses;

import com.developer.smmousavi.foodino.models.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class RecipeSearchResponse {

    @SerializedName("count")
    @Expose
    private int mCount;

    @SerializedName("recipes")
    @Expose
    private List<Recipe> mRecipes;


    public int getCount() {
        return mCount;
    }

    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecipeSearchResponse{" +
            "mCount=" + mCount +
            ", mRecipes=" + mRecipes +
            '}';
    }
}
