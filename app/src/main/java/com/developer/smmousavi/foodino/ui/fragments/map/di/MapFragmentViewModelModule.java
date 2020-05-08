package com.developer.smmousavi.foodino.ui.fragments.map.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.ui.fragments.map.MapFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MapFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MapFragmentViewModel.class)
    public abstract ViewModel bindMapFragmentViewModel(MapFragmentViewModel viewModel);
}
