package com.developer.smmousavi.foodino.repositories;

import android.content.Context;

import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.AppExecutors;
import com.developer.smmousavi.foodino.network.reciperesponses.ApiResponse;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;
import com.developer.smmousavi.foodino.network.util.NetworkBoundResource;
import com.developer.smmousavi.foodino.presistence.recipe.RecipeDAO;
import com.developer.smmousavi.foodino.presistence.recipe.RecipeDatabase;
import com.developer.smmousavi.foodino.network.util.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class RecipeRepository {

    private static RecipeRepository sInstance;
    private RecipeDAO mRecipeDAO;

    public static RecipeRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RecipeRepository(context);
        }
        return sInstance;
    }

    public RecipeRepository(Context context) {
        mRecipeDAO = RecipeDatabase.getInstance(context).getRecipeDao();
    }

    public LiveData<Resource<List<Recipe>>> searchRecipesApi(final String  query, final int pageNumber) {
        return new NetworkBoundResource<List<Recipe>, RecipeSearchResponse>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull RecipeSearchResponse item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Recipe> data) {
                return true ;
            }

            @NonNull
            @Override
            protected LiveData<List<Recipe>> loadFromDb() {
                return mRecipeDAO.searchRecipes(query, pageNumber);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<RecipeSearchResponse>> createCall() {
                return null;
            }
        }.getAsLiveData();

    }

}
