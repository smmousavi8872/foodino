package com.developer.smmousavi.foodino.ui.fragments.mapsearch.di;

import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;
import com.developer.smmousavi.foodino.network.api.MapSearchRestApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MapSearchFragmentModule {

    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

    @Provides
    static MapSearchRestApi provideMapSearchApi(Retrofit retrofit) {
        return retrofit.create(MapSearchRestApi.class);
    }
}
