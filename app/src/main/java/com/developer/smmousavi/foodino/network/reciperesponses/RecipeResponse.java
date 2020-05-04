package com.developer.smmousavi.foodino.network.reciperesponses;

import com.developer.smmousavi.foodino.models.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class RecipeResponse {

    @SerializedName("recipe")
    @Expose
    private Recipe mRecipe;

    public Recipe getRecipe() {
        return mRecipe;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecipeResponse{" +
            "mRecipe=" + mRecipe +
            '}';
    }
}
