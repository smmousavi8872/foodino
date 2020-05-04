package com.developer.smmousavi.foodino.network.clients;

import android.util.Log;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.factory.RecipeRestApiFactory;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeResponse;

import java.io.IOException;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRecipeApiClient {

    private static GetRecipeApiClient sInstance;

    private MutableLiveData<Recipe> mRecipeLD;
    private MutableLiveData<Boolean> mRecipeRequestFailed;
    // private GetRecipesRunnable mGetRecipeRunnable;

    public static GetRecipeApiClient getInstance() {
        if (sInstance == null)
            sInstance = new GetRecipeApiClient();
        return sInstance;
    }

    private GetRecipeApiClient() {
        mRecipeLD = new MutableLiveData<>();
        mRecipeRequestFailed = new MutableLiveData<>();
    }

    public MutableLiveData<Recipe> getRecipeLd() {
        return mRecipeLD;
    }

    public MutableLiveData<Boolean> getRecipeRequestFailedLd() {
        return mRecipeRequestFailed;
    }


    public void getRecipeApi(String recipeId) {
        RecipeRestApiFactory.create()
            .getRecipe(Constants.RECIPE_API_KEY, recipeId)
            .enqueue(new Callback<RecipeResponse>() {
                @Override
                public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                    try {
                        if (response.code() == 200) {
                            Recipe recipe = response.body().getRecipe();
                            mRecipeLD.postValue(recipe);
                            mRecipeRequestFailed.postValue(false);
                        } else {
                            String err = response.errorBody().string();
                            mRecipeLD.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        mRecipeLD.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<RecipeResponse> call, Throwable t) {
                    Log.i("OBSERVER_CLIENT", "connection faild");
                    mRecipeRequestFailed.postValue(true);
                }
            });
    }

    /*   public void getRecipeApi(String recipeId) {
        if (mGetRecipeRunnable != null)
            mGetRecipeRunnable = null;
        mGetRecipeRunnable = new GetRecipesRunnable(recipeId);
        final Future handler = AppExecutors.getInstance().getNetworkIO().submit(mGetRecipeRunnable);

        mRecipeRequestFailed.postValue(false);
        // Set a timeout for the data refresh
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            // let the user know it timed out
            Log.i("OBSERVER_CLIENT", "connection failed");
            mRecipeRequestFailed.postValue(true);
            handler.cancel(true);
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }*/


    /*private class GetRecipesRunnable implements Runnable {

        private String mRecipeId;
        private boolean mCancelRequest;

        public GetRecipesRunnable(String recipeId) {
            this.mRecipeId = recipeId;
            mCancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getRecipe(mRecipeId).execute();
                int code = response.code();
                if (mCancelRequest)
                    return;

                if (response.code() == 200) {
                    Recipe recipe = ((RecipeResponse) response.body()).getRecipe();
                    mRecipeLD.postValue(recipe);
                } else {
                    String err = response.errorBody().string();
                    mRecipeLD.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mRecipeLD.postValue(null);
            }
        }

        private Call<RecipeResponse> getRecipe(String recipeId) {
            return RestApiFactory.create().getRecipe(
                Constants.API_KEY,
                recipeId);
        }

        public void cancelRequest() {
            mCancelRequest = true;
        }
    }

    public void cancelGetRecipe() {
        if (mGetRecipeRunnable != null) {
            mGetRecipeRunnable.cancelRequest();
        }
    }*/
}
