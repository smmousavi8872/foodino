package com.developer.smmousavi.foodino.repositories;

import android.util.Log;

import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.clients.SpecialRecipesApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

public class SpecialRecipeRepoitory {

    private static SpecialRecipeRepoitory sInstance;

    private SpecialRecipesApiClient mSpecialRecipesApiClient;
    private String mSpecialRecipesQuery;
    private int mSpecialRecipesPageNum;
    private MutableLiveData<Boolean> mIsQueryExhausted;
    private MediatorLiveData<List<Recipe>> mRecipesMedLd;

    private SpecialRecipeRepoitory() {
        mSpecialRecipesApiClient = SpecialRecipesApiClient.getInstance();
        mIsQueryExhausted = new MutableLiveData<>();
        mRecipesMedLd = new MediatorLiveData<>();
        initMediators();
    }

    public static SpecialRecipeRepoitory getInstance() {
        if (sInstance == null) {
            sInstance = new SpecialRecipeRepoitory();
        }
        return sInstance;
    }

    private void initMediators() {
        LiveData<List<Recipe>> recpieListApiSource = mSpecialRecipesApiClient.getSpecialRecipesLd();
        mRecipesMedLd.addSource(recpieListApiSource, recipes -> {
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
        // returning medeitorLiveData becuase it is going to change
        //
        return mRecipesMedLd;
    }

    public MutableLiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhausted;
    }

    public void getSpecialRecipes(String query, int pageNum) {
        mSpecialRecipesQuery = query;
        mSpecialRecipesPageNum = pageNum;
        mIsQueryExhausted.setValue(false);
        if (mSpecialRecipesPageNum == 0)
            mSpecialRecipesPageNum = 1;
        mSpecialRecipesApiClient.getSpecialRecipesApi(mSpecialRecipesQuery, mSpecialRecipesPageNum);
    }

    public void getSpecialRecipesNextPage() {
        Log.i("NEXT_PAGE", "Get Next page. current page is: " + mSpecialRecipesPageNum);
        mSpecialRecipesApiClient.getSpecialRecipesApi(mSpecialRecipesQuery, mSpecialRecipesPageNum++);
    }

}
