package com.developer.smmousavi.foodino.network.clients;

import android.util.Log;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.AppExecutors;
import com.developer.smmousavi.foodino.network.factory.RecipeRestApiFactory;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

public class SpecialRecipesApiClient {

    public static final String TAG = "RecipeApiClient";

    private static SpecialRecipesApiClient sInstance;

    private MutableLiveData<List<Recipe>> mSpecialRecipesLD;
    private SearchRecipesRunnable mSpecialRecipesRunnable;

    public static SpecialRecipesApiClient getInstance() {
        if (sInstance == null)
            sInstance = new SpecialRecipesApiClient();
        return sInstance;
    }

    private SpecialRecipesApiClient() {
        mSpecialRecipesLD = new MutableLiveData<>();
    }

    public MutableLiveData<List<Recipe>> getSpecialRecipesLd() {
        return mSpecialRecipesLD;
    }


    public void getSpecialRecipesApi(String query, int pageNumber) {
        if (mSpecialRecipesRunnable != null)
            mSpecialRecipesRunnable = null;
        mSpecialRecipesRunnable = new SearchRecipesRunnable(query, pageNumber);
        final Future handler = AppExecutors.getInstance().getNetworkIO().submit(mSpecialRecipesRunnable);

        // Set a timeout for the data refresh
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            // let the user know it timed out
            handler.cancel(true);
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }


    private class SearchRecipesRunnable implements Runnable {

        private String mQuery;
        private int mPageNumber;
        private boolean mCancelRequest;

        public SearchRecipesRunnable(String query, int pageNumber) {
            this.mQuery = query;
            this.mPageNumber = pageNumber;
            mCancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getSpecialRecipes(mQuery, mPageNumber).execute();
                int code = response.code();
                if (mCancelRequest)
                    return;
                if (response.code() == 200) {
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse) response.body()).getRecipes());
                    if (mPageNumber == 1) {
                        // page numbger is 1 we just post a list of recipes to the live data
                        mSpecialRecipesLD.postValue(list);
                    } else {
                        // page numbger is not 1, we add the recieved recipes to the last list and then post it to the live data.
                        List<Recipe> currentRecipes = mSpecialRecipesLD.getValue();
                        currentRecipes.addAll(list);
                        mSpecialRecipesLD.postValue(currentRecipes);
                    }
                    Log.i("RECIPE_RESPONSE",
                        "page = " + mPageNumber + "response count = " + ((RecipeSearchResponse) response.body()).getCount());
                } else {
                    String err = response.errorBody().string();
                    Log.e(TAG, "run: " + err);
                    mSpecialRecipesLD.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mSpecialRecipesLD.postValue(null);
            }
        }

        private Call<RecipeSearchResponse> getSpecialRecipes(String query, int pageNumber) {
            return RecipeRestApiFactory.create().getSpecialRecipes(
                Constants.RECIPE_API_KEY,
                query,
                String.valueOf(pageNumber));
        }


        public void cancelRequest() {
            mCancelRequest = true;
        }
    }


    public void cancelSearchRecipeRequest() {
        if (mSpecialRecipesRunnable != null) {
            mSpecialRecipesRunnable.cancelRequest();
        }
    }
}
