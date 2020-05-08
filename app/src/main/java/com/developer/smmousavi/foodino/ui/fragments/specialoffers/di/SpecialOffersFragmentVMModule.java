package com.developer.smmousavi.foodino.ui.fragments.specialoffers.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.ui.fragments.specialoffers.SpecialOffersFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SpecialOffersFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(SpecialOffersFragmentViewModel.class)
    public abstract ViewModel bindSpecialOffersFragmentViewModel(SpecialOffersFragmentViewModel specialOffersFragmentViewModel);
}
