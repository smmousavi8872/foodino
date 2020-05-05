package com.developer.smmousavi.foodino.network.clients;

import android.util.Log;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.AppExecutors;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

public class HomeApiClient {
    public static final String TAG = "RecipeApiClient";

    private static HomeApiClient sInstance;

    private MutableLiveData<List<Recipe>> mPreviewRecipesLD;

    private PreviewRecipesRunnable mPreviewRecipesRunnable;

    public static HomeApiClient getInstance() {
        if (sInstance == null)
            sInstance = new HomeApiClient();
        return sInstance;
    }

    private HomeApiClient() {
        mPreviewRecipesLD = new MutableLiveData<>();
    }

    public MutableLiveData<List<Recipe>> getPreviewRecipesLd() {
        return mPreviewRecipesLD;
    }

    public void getPreviewRecipesApi(String query, int pageNumber) {
        if (mPreviewRecipesRunnable != null)
            mPreviewRecipesRunnable = null;
        mPreviewRecipesRunnable = new PreviewRecipesRunnable(query, pageNumber);
        final Future handler = AppExecutors.getInstance().getNetworkIO().submit(mPreviewRecipesRunnable);

        // Set a timeout for the data refresh
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            // let the user know it timed out
            handler.cancel(true);
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }


    private class PreviewRecipesRunnable implements Runnable {

        private String mQuery;
        private int mPageNumber;
        private boolean mCancelRequest;

        public PreviewRecipesRunnable(String query, int pageNumber) {
            this.mQuery = query;
            this.mPageNumber = pageNumber;
            mCancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getPreviewRecipes(mQuery, mPageNumber).execute();
                int code = response.code();
                if (mCancelRequest)
                    return;
                if (response.code() == 200) {
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse) response.body()).getRecipes());
                    if (mPageNumber == 1) {
                        // page number is 1 we just post a list of recipes to the live data
                        mPreviewRecipesLD.postValue(list);
                    } else {
                        // page number is not 1, we add the received recipes to the last list and then post it to the live data.
                        List<Recipe> currentRecipes = mPreviewRecipesLD.getValue();
                        currentRecipes.addAll(list);
                        mPreviewRecipesLD.postValue(currentRecipes);
                    }
                    Log.i("RECIPE_RESPONSE",
                        "page = " + mPageNumber + "response count = " + ((RecipeSearchResponse) response.body()).getCount());
                } else {
                    String err = response.errorBody().string();
                    Log.e(TAG, "run: " + err);
                    mPreviewRecipesLD.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mPreviewRecipesLD.postValue(null);
            }
        }

        private Call<RecipeSearchResponse> getPreviewRecipes(String query, int pageNumber) {
           /* return RecipeRestApiFactory.create().getSpecialRecipes(
                Constants.RECIPE_API_KEY,
                query,
                String.valueOf(pageNumber));*/
           return null;
        }


        public void cancelRequest() {
            mCancelRequest = true;
        }
    }


    public void cancelPreviewRecipesRequest() {
        if (mPreviewRecipesRunnable != null) {
            mPreviewRecipesRunnable.cancelRequest();
        }
    }
}
