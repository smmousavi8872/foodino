package com.developer.smmousavi.foodino.ui.fragments.shoppingbasket;

import android.app.Application;
import android.util.Log;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.util.Resource;
import com.developer.smmousavi.foodino.repositories.ShoppingBasketRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import static com.developer.smmousavi.foodino.ui.fragments.productdetail.ProductDetailFragment.TAG;

public class ShoppingBasketFragmentViewModel extends BaseViewModel {

    private static final String QUERY_EXHAUSTED = "Suggested recipes query exhausted.";
    private ShoppingBasketRepository mShoppingBasketRepository;
    private String mQuery;
    private int mPageNumber;
    private boolean mIsPerformingQuery;
    private boolean mCancelRequest;
    private boolean mIsQueryExhausted;
    private MediatorLiveData<Resource<List<Recipe>>> recipesMLD;


    public MediatorLiveData<Resource<List<Recipe>>> getRecipesMLD() {
        return recipesMLD;
    }

    @Inject
    public ShoppingBasketFragmentViewModel(Application app) {
        super(app);
        recipesMLD = new MediatorLiveData<>();
        mShoppingBasketRepository = ShoppingBasketRepository.getInstance(app);
    }

    /**
     * @HardCoded Specific api should be implemented in server
     */
    public void getSuggestedRecipes() {
        searchRecipesApi("cookie", 1);
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (!mIsPerformingQuery) {
            if (pageNumber == 0) {
                pageNumber = 1;
            }
            this.mPageNumber = pageNumber;
            this.mQuery = query;
            mIsQueryExhausted = false;
            executeSearch();
        }
    }

    public void executeSearch() {
        mIsPerformingQuery = true;
        mCancelRequest = false;
        final LiveData<Resource<List<Recipe>>> repoSource = mShoppingBasketRepository.getSuggestedRecipes(mQuery, mPageNumber);
        recipesMLD.addSource(repoSource, listResource -> {
            //onChange
            if (listResource != null) {
                recipesMLD.setValue(listResource);
                if (listResource.status == Resource.Status.SUCCESS) {
                    mIsPerformingQuery = false;
                    if (listResource.data != null) {
                        if (listResource.data.size() % 30 != 0) {
                            Log.d(TAG, "executeSearch: query is exhausted.");
                            recipesMLD.setValue(new Resource<>(Resource.Status.ERROR, listResource.data, QUERY_EXHAUSTED));
                        }
                    }
                    recipesMLD.removeSource(repoSource);
                } else if (listResource.status == Resource.Status.ERROR) {
                    mIsPerformingQuery = false;
                    recipesMLD.removeSource(repoSource);
                }
            } else {
                recipesMLD.removeSource(repoSource);
            }
        });
    }

    public void cancelSearchRequest() {
        if (mIsPerformingQuery) {
            Log.d(TAG, "cancelSearchRequest: canceling search reqeust");
        }
    }

}
