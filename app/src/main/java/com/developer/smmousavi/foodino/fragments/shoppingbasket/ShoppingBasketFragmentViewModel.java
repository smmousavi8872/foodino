package com.developer.smmousavi.foodino.fragments.shoppingbasket;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.repositories.ShoppingBasketRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

public class ShoppingBasketFragmentViewModel extends BaseViewModel {

    private ShoppingBasketRepository mShoppingBasketRepository;

    @Inject
    public ShoppingBasketFragmentViewModel() {
        mShoppingBasketRepository = ShoppingBasketRepository.getInstance();
    }


    public MutableLiveData<List<Recipe>> getRecipeLd() {
        return mShoppingBasketRepository.getSuggestedRecipesLd();
    }

    public void getSuggestedRecipes(String query, int pageNum) {
        mShoppingBasketRepository.getSuggestedRecipes(query, pageNum);
    }
}
