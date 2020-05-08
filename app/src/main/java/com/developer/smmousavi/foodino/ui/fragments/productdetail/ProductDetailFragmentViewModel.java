package com.developer.smmousavi.foodino.ui.fragments.productdetail;

import android.app.Application;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.repositories.GetRecipeRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

public class ProductDetailFragmentViewModel extends BaseViewModel {

    private GetRecipeRepository mGetRecipeRepository;
    private String mRecipeId;
    private boolean mRetrieveRecipe;

    @Inject
    public ProductDetailFragmentViewModel(Application app) {
        super(app);
        mGetRecipeRepository = GetRecipeRepository.getInstance();
        mRetrieveRecipe = false;
    }

    public MutableLiveData<Recipe> getRecipeLd() {
        return mGetRecipeRepository.getRecipeLd();
    }

    public MutableLiveData<Boolean> getRecipeRequestFailedLd() {
        return mGetRecipeRepository.getRecipeRequestFailedLd();
    }

    public void getRecipe(String recipeId) {
        mRecipeId = recipeId;
        mGetRecipeRepository.getRecipe(recipeId);
    }


    public String getRecipeId() {
        return mRecipeId;
    }

    public boolean didRetrieveRecipe() {
        return mRetrieveRecipe;
    }

    public void setRetrieveRecipe(boolean retrieveRecipe) {
        this.mRetrieveRecipe = retrieveRecipe;
    }
}
