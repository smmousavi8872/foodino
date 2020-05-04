package com.developer.smmousavi.foodino.factory.viewmodel.di;

import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory viewModelProviderFactory);
}
