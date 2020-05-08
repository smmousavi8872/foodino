package com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.ShoppingBasketFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ShoppingBasketFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingBasketFragmentViewModel.class)
    public abstract ViewModel bindShoppingBasketViewModel(ShoppingBasketFragmentViewModel shoppingBasketFragmentViewModel);

}
