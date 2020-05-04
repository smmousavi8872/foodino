package com.developer.smmousavi.foodino.network.clients;

import android.util.Log;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.factory.RecipeRestApiFactory;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

public class ShoppingBasketApiClient {

    public static final String TAG = "RecipeApiClient";

    private static ShoppingBasketApiClient sInstance;

    private MutableLiveData<List<Recipe>> mSuggestedRecipesLd;
    private SuggestedRecipesRunnable mSuggestedRecipesRunnable;

    public static ShoppingBasketApiClient getInstance() {
        if (sInstance == null)
            sInstance = new ShoppingBasketApiClient();
        return sInstance;
    }

    private ShoppingBasketApiClient() {
        mSuggestedRecipesLd = new MutableLiveData<>();
    }

    public MutableLiveData<List<Recipe>> getSuggestedRecipesLd() {
        return mSuggestedRecipesLd;
    }

    public void getSuggestedRecipesApi(String query, int pageNumber) {
        if (mSuggestedRecipesRunnable != null)
            mSuggestedRecipesRunnable = null;
        mSuggestedRecipesRunnable = new SuggestedRecipesRunnable(query, pageNumber);
        /*final Future handler = AppExecutors.getInstance().getNetworkIO().submit(mSuggestedRecipesRunnable);

        // Set a timeout for the data refresh
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            // let the user know it timed out
            handler.cancel(true);
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);*/
    }



    private class SuggestedRecipesRunnable implements Runnable {

        private String mQuery;
        private int mPageNumber;
        private boolean mCancelRequest;

        public SuggestedRecipesRunnable(String query, int pageNumber) {
            this.mQuery = query;
            this.mPageNumber = pageNumber;
            mCancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getSuggestedRecipes(mQuery, mPageNumber).execute();
                int code = response.code();
                if (mCancelRequest)
                    return;
                if (response.code() == 200) {
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse) response.body()).getRecipes());
                    if (mPageNumber == 1) {
                        // page numbger is 1 we just post a list of recipes to the live data
                        mSuggestedRecipesLd.postValue(list);
                    } else {
                        // page numbger is not 1, we add the recieved recipes to the last list and then post it to the live data.
                        List<Recipe> currentRecipes = mSuggestedRecipesLd.getValue();
                        currentRecipes.addAll(list);
                        mSuggestedRecipesLd.postValue(currentRecipes);
                    }
                    Log.i("RECIPE_RESPONSE",
                        "page = " + mPageNumber + "response count = " + ((RecipeSearchResponse) response.body()).getCount());
                } else {
                    String err = response.errorBody().string();
                    Log.e(TAG, "run: " + err);
                    mSuggestedRecipesLd.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mSuggestedRecipesLd.postValue(null);
            }
        }

        private Call<RecipeSearchResponse> getSuggestedRecipes(String query, int pageNumber) {
            return RecipeRestApiFactory.create().getPreviewRecipes(
                Constants.RECIPE_API_KEY,
                query,
                String.valueOf(pageNumber));
        }

        public void cancelRequest() {
            mCancelRequest = true;
        }
    }
}
