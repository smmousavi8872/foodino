package com.developer.smmousavi.foodino.fragments.home.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.fragments.home.HomeFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class HomeFragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel.class)
    public abstract ViewModel bindHomeFragmentViewModel(HomeFragmentViewModel homeFragmentViewModel);
}
