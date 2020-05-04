package com.developer.smmousavi.foodino.repositories;

import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.clients.GetRecipeApiClient;

import androidx.lifecycle.MutableLiveData;

public class GetRecipeRepository {

    private static GetRecipeRepository sInstance;

    private GetRecipeApiClient mGetRecipeApiClient;

    public static GetRecipeRepository getInstance() {
        if (sInstance == null) {
            sInstance = new GetRecipeRepository();
            return sInstance;
        }
        return sInstance;
    }

    private GetRecipeRepository() {
        mGetRecipeApiClient = GetRecipeApiClient.getInstance();
    }

    public MutableLiveData<Boolean> getRecipeRequestFailedLd() {
        return mGetRecipeApiClient.getRecipeRequestFailedLd();
    }

    public MutableLiveData<Recipe> getRecipeLd() {
        return mGetRecipeApiClient.getRecipeLd();
    }

    public void getRecipe(String recipeId) {
        mGetRecipeApiClient.getRecipeApi(recipeId);
    }

}
