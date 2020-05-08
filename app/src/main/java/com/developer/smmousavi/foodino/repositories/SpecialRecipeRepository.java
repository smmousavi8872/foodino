package com.developer.smmousavi.foodino.repositories;

import android.content.Context;
import android.util.Log;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.AppExecutors;
import com.developer.smmousavi.foodino.network.factory.RecipeRestApiFactory;
import com.developer.smmousavi.foodino.network.reciperesponses.ApiResponse;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;
import com.developer.smmousavi.foodino.network.util.NetworkBoundResource;
import com.developer.smmousavi.foodino.network.util.Resource;
import com.developer.smmousavi.foodino.presistence.recipe.RecipeDAO;
import com.developer.smmousavi.foodino.presistence.recipe.RecipeDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class SpecialRecipeRepository {
    private static final String TAG = "RecipeRepositoryTag";

    private static SpecialRecipeRepository sInstance;
    private RecipeDAO mRecipeDAO;

    public static SpecialRecipeRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SpecialRecipeRepository(context);
        }
        return sInstance;
    }

    private SpecialRecipeRepository(Context context) {
        mRecipeDAO = RecipeDatabase.getInstance(context).getRecipeDao();
    }

    public LiveData<Resource<List<Recipe>>> getSpecialRecipes(final String query, final int pageNumber) {
        return new NetworkBoundResource<List<Recipe>, RecipeSearchResponse>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull RecipeSearchResponse item) {
                if (item.getRecipes() != null) {
                    //  recipe list will be null if the api key is expired
                    Recipe[] recipes = new Recipe[item.getRecipes().size()];
                    int index = 0;
                    for (long rowId : mRecipeDAO.insertRecipes(item.getRecipes().toArray(recipes))) {
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                            // if the recipe already exists... I don't want to set the ingredients or timestamp b/c
                            // they will be erased
                            mRecipeDAO.updateRecipe(
                                recipes[index].getRecipeId(),
                                recipes[index].getTitle(),
                                recipes[index].getPublisher(),
                                recipes[index].getImageUrl(),
                                recipes[index].getSocialRank()
                            );
                        }
                        index++;
                    }
                }
            }
            @Override
            protected boolean shouldFetch(@Nullable List<Recipe> data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Recipe>> loadFromDb() {
                return mRecipeDAO.searchRecipes(query, pageNumber);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<RecipeSearchResponse>> createCall() {
                return RecipeRestApiFactory.create()
                    .getSpecialRecipes(Constants.MAP_API_KEY, query, String.valueOf(pageNumber));
            }
        }.getAsLiveData();

    }

}
