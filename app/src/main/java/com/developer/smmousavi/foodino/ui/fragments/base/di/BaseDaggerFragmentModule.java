package com.developer.smmousavi.foodino.ui.fragments.base.di;

import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseDaggerFragmentModule {
    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

}
