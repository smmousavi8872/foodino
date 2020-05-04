package com.developer.smmousavi.foodino.fragments.categorylist.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.fragments.categorylist.CategoryListFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CategoryListFragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryListFragmentViewModel.class)
    public abstract ViewModel bindCategoryListFragmentViewModel(CategoryListFragmentViewModel categoryListFragmentViewModel);
}
