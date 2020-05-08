package com.developer.smmousavi.foodino.ui.activities.base.di;

import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseDaggerAppCompatActivityModule {

    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

}
