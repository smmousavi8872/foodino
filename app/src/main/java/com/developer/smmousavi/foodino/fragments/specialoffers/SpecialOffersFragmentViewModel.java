package com.developer.smmousavi.foodino.fragments.specialoffers;

import android.app.Application;
import android.util.Log;

import com.developer.smmousavi.foodino.activities.specialoffers.SpecialOffersActivity;
import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.util.Resource;
import com.developer.smmousavi.foodino.repositories.SpecialRecipeRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import static com.developer.smmousavi.foodino.fragments.productdetail.ProductDetailFragment.TAG;

public class SpecialOffersFragmentViewModel extends BaseViewModel {

    private SpecialRecipeRepository mSpecialRecipeRepository;
    private MediatorLiveData<Resource<List<Recipe>>> recipesMLD = new MediatorLiveData<>();

    @Inject
    public SpecialOffersFragmentViewModel(Application app) {
        super(app);
        mSpecialRecipeRepository = SpecialRecipeRepository.getInstance(app);
    }

    public MediatorLiveData<Resource<List<Recipe>>> getSpecialRecipesMLD() {
        return recipesMLD;
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
        final LiveData<Resource<List<Recipe>>> repoSource = mSpecialRecipeRepository.getSpecialRecipes(query, pageNumber);
        recipesMLD.addSource(repoSource, listResource -> {
            //onChange

            //react to the data
            recipesMLD.setValue(listResource);

        });
    }
}
