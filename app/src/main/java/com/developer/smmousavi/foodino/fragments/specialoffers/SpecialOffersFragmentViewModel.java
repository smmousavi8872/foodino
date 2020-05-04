package com.developer.smmousavi.foodino.fragments.specialoffers;

import android.util.Log;

import com.developer.smmousavi.foodino.activities.specialoffers.SpecialOffersActivity;
import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.repositories.SpecialRecipeRepoitory;
import com.developer.smmousavi.foodino.network.util.NetworkState;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import static com.developer.smmousavi.foodino.fragments.productdetail.ProductDetailFragment.TAG;

public class SpecialOffersFragmentViewModel extends BaseViewModel {

    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Recipe>> articleLiveData;
    private SpecialRecipeRepoitory mSpecialRecipeRepository;

    @Inject
    public SpecialOffersFragmentViewModel() {
        mSpecialRecipeRepository = SpecialRecipeRepoitory.getInstance();
    }

    /*
     * Step 1: We are initializing an Executor class
     * Step 2: We are getting an instance of the DataSourceFactory class
     * Step 3: We are initializing the network state liveData as well.
     *         This will update the UI on the network changes that take place
     *         For instance, when the data is getting fetched, we would need
     *         to display a loader and when data fetching is completed, we
     *         should hide the loader.
     * Step 4: We need to configure the PagedList.Config.
     * Step 5: We are initializing the pageList using the config we created
     *         in Step 4 and the DataSourceFactory we created from Step 2
     *         and the executor we initialized from Step 1.
     */

      private void init() {
        executor = Executors.newFixedThreadPool(5);
    }

    public void getSpecialRecipes(String query, int page) {
        mSpecialRecipeRepository.getSpecialRecipes(query, page);
    }

    public MutableLiveData<List<Recipe>> getSpecialRecipesLd() {
        return mSpecialRecipeRepository.getSpecialRecipesLd();
    }

    public void searchNextPage() {
        if (!mSpecialRecipeRepository.isQueryExhausted().getValue()) {
            Log.i(TAG, "searchNextPage: ");
            mSpecialRecipeRepository.getSpecialRecipesNextPage();
        }
    }

    public MutableLiveData<Boolean> isQueryExhaustedLd() {
        return mSpecialRecipeRepository.isQueryExhausted();
    }

    /**
     * @HardCode queries should change to specific ones according to the OfferType
     * TODO: change
     */
    public void setSpecialOffersProductList(SpecialOffersActivity.OfferType offerType) {
        switch (offerType) {
            //TODO: newestApi
            case NEWEST:
                mSpecialRecipeRepository.getSpecialRecipes("chicken", 1);
                break;
            //TODO:mostSeenApi
            case MOST_SEEN:
                mSpecialRecipeRepository.getSpecialRecipes("beef", 1);
                break;
            //TODO: mostSoldApi
            case MOST_SOLD:
                mSpecialRecipeRepository.getSpecialRecipes("chips", 1);
                break;
            //TODO: specialOfferApi
            case SPECIAL_OFFER:
                mSpecialRecipeRepository.getSpecialRecipes("pasta", 1);
                break;
        }
    }

    public void clear() {
    }
}
