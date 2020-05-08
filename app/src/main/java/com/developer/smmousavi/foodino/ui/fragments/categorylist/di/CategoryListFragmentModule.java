package com.developer.smmousavi.foodino.ui.fragments.categorylist.di;

import android.app.Application;

import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.repositories.HomeRepository;
import com.developer.smmousavi.foodino.ui.fragments.categorylist.CategoryListTabLayoutFragment;

import java.util.LinkedHashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

@Module
public class CategoryListFragmentModule {

    @Provides
    public static List<Category> provideCategoryList(Application app) {
        return HomeRepository.getInstance(app).getCategoryList();
    }

    @Provides
    public LinkedHashMap<String, Fragment> provideFragmentMap(List<Category> categoryList) {
        LinkedHashMap<String, Fragment> fragmentMap = new LinkedHashMap<>();
        for (Category category : categoryList) {
            Fragment categoryFragment = CategoryListTabLayoutFragment.newInstance(category);
            fragmentMap.put(category.title(), categoryFragment);
            int[] arr = new int[4];
        }
        return fragmentMap;
    }


}
