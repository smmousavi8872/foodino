package com.developer.smmousavi.foodino.repositories;

import android.content.Context;
import android.util.Log;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.AppExecutors;
import com.developer.smmousavi.foodino.network.clients.SpecialRecipesApiClient;
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
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

public class SpecialRecipeRepository {
    private static final String TAG = "RecipeRepositoryTag";

    private static SpecialRecipeRepository sInstance;

    private SpecialRecipesApiClient mSpecialRecipesApiClient;
    private String mSpecialRecipesQuery;
    private int mSpecialRecipesPageNum;
    private MutableLiveData<Boolean> mIsQueryExhausted;
    private MediatorLiveData<List<Recipe>> mRecipesMedLd;
    private RecipeDAO mRecipeDAO;

    private SpecialRecipeRepository(Context context) {
        mIsQueryExhausted = new MutableLiveData<>();
        mRecipesMedLd = new MediatorLiveData<>();
        mRecipeDAO = RecipeDatabase.getInstance(context).getRecipeDao();
        initMediators();
    }

    public static SpecialRecipeRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SpecialRecipeRepository(context);
        }
        return sInstance;
    }

    private void initMediators() {
        LiveData<List<Recipe>> recipeListApiSource = mSpecialRecipesApiClient.getSpecialRecipesLd();
        mRecipesMedLd.addSource(recipeListApiSource, recipes -> {
            if (recipes != null) {
                mRecipesMedLd.setValue(recipes);
                doneQuery(recipes);
            } else {
                // search database cache
                doneQuery(null);
            }
        });
    }

    private void doneQuery(List<Recipe> list) {
        if (list != null) {
            if (list.size() % 30 != 0)
                mIsQueryExhausted.setValue(true);
        } else
            mIsQueryExhausted.setValue(true);
    }

    public MutableLiveData<List<Recipe>> getSpecialRecipesLd() {
        // returning mediatorLiveData because it is going to change
        //
        return mRecipesMedLd;
    }

    public MutableLiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhausted;
    }



    public void getSpecialRecipesNextPage() {
        Log.i("NEXT_PAGE", "Get Next page. current page is: " + mSpecialRecipesPageNum);
        mSpecialRecipesApiClient.getSpecialRecipesApi(mSpecialRecipesQuery, mSpecialRecipesPageNum++);
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
