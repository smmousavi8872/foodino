package com.developer.smmousavi.foodino.ui.fragments.mapsearch.di;

import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelKey;
import com.developer.smmousavi.foodino.ui.fragments.mapsearch.MapSearchFragmentViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MapSearchFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapSearchFragmentViewModel.class)
    public abstract ViewModel bindMapSearchVMModule(MapSearchFragmentViewModel mapSearchFragmentViewModel);

}
