package com.developer.smmousavi.foodino.fragments.home;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Banner;
import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.repositories.HomeRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class HomeFragmentViewModel extends BaseViewModel {

    private HomeRepository mSpecialRecipeRepo;

    @Inject
    public HomeFragmentViewModel() {
        mSpecialRecipeRepo = HomeRepository.getInstance();
    }


    public MutableLiveData<List<Recipe>> getPreviewRecipesLd() {
        return mSpecialRecipeRepo.getPreviewRecipesLd();
    }

    public void searchPreviewRecipes(String query, int pageNum) {
        mSpecialRecipeRepo.getPreviewRecipes(query, pageNum);
    }




    public LiveData<List<Banner>> getBannerListLiveData() {
        return mSpecialRecipeRepo.getSliderBannerList();
    }

    public LiveData<List<Category>> getCategoryPreviewListLiveData() {
        return mSpecialRecipeRepo.getCategoryPreviewList();
    }

    public LiveData<List<Product>> getProductPreviewListLiveData() {
        return mSpecialRecipeRepo.getProductPreviewList();
    }

}
