package com.developer.smmousavi.foodino.ui.fragments.finalizeshop.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.ui.fragments.finalizeshop.FinalizeShopFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class FinalizeShopFragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FinalizeShopFragmentViewModel.class)
    public abstract ViewModel bindFinalizeShopFragmentViewModel(FinalizeShopFragmentViewModel finalizeShopFragmentViewModel);
}
