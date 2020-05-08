package com.developer.smmousavi.foodino.ui.fragments.categorylist.di;

import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryListTabLayoutFragmentModule {

    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

}
