package com.developer.smmousavi.foodino.fragments.productdetail.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.fragments.productdetail.ProductDetailFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ProductDetailFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailFragmentViewModel.class)
    public abstract ViewModel bindProductDetailFragmentVMModule(ProductDetailFragmentViewModel productDetailFragmentViewModel);

}
