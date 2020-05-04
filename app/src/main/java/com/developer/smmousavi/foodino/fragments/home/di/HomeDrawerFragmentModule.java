package com.developer.smmousavi.foodino.fragments.home.di;

import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeDrawerFragmentModule {
    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

}
