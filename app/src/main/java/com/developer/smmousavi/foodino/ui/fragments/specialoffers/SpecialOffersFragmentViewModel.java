package com.developer.smmousavi.foodino.ui.fragments.specialoffers;

import android.app.Application;
import android.util.Log;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.util.Resource;
import com.developer.smmousavi.foodino.repositories.SpecialRecipeRepository;
import com.developer.smmousavi.foodino.ui.activities.specialoffers.SpecialOffersActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import static com.developer.smmousavi.foodino.ui.fragments.productdetail.ProductDetailFragment.TAG;

public class SpecialOffersFragmentViewModel extends BaseViewModel {

    private SpecialRecipeRepository mSpecialRecipeRepository;
    private MediatorLiveData<Resource<List<Recipe>>> recipesMLD = new MediatorLiveData<>();

    private boolean mIsQueryExhausted;
    private boolean mIsPerformingQuery;
    private boolean mCancelRequest;
    private int mPageNumber;
    private String mQuery;
    public static final String QUERY_EXHAUSTED = "No More Results";

    @Inject
    public SpecialOffersFragmentViewModel(Application app) {
        super(app);
        mSpecialRecipeRepository = SpecialRecipeRepository.getInstance(app);
    }

    public MediatorLiveData<Resource<List<Recipe>>> getSpecialRecipesMLD() {
        return recipesMLD;
    }

    /**
     * @HardCode queries should change to specific ones according to the OfferType
     * TODO: change
     */
    public void setSpecialOffersProductList(SpecialOffersActivity.OfferType offerType) {
        switch (offerType) {
            //TODO: newestApi
            case NEWEST:
                searchRecipesApi("Chicken", 1);
                break;
            //TODO:mostSeenApi
            case MOST_SEEN:
                searchRecipesApi("beef", 1);
                break;
            //TODO: mostSoldApi
            case MOST_SOLD:
                searchRecipesApi("chips", 1);
                break;
            //TODO: specialOfferApi
            case SPECIAL_OFFER:
                searchRecipesApi("pasta", 1);
                break;
        }
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

    public void searchNextPage() {
        if (!mIsQueryExhausted && !mIsPerformingQuery) {
            Log.i(TAG, "searchNextPage: ");
            mPageNumber++;
            executeSearch();
        }
    }

    public void executeSearch() {
        mIsPerformingQuery = true;
        mCancelRequest = false;
        final LiveData<Resource<List<Recipe>>> repoSource = mSpecialRecipeRepository.getSpecialRecipes(mQuery, mPageNumber);
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
