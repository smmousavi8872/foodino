package com.developer.smmousavi.foodino.ui.fragments.map.di;

import com.developer.smmousavi.foodino.network.api.MapRouteRestApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MapFragmentModule {

    @Provides
    static MapRouteRestApi provideMapRouteApi(Retrofit retrofit) {
        return retrofit.create(MapRouteRestApi.class);
    }

}
