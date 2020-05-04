package com.developer.smmousavi.foodino.fragments.map;

import android.util.Log;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.network.api.MapRouteRestApi;
import com.developer.smmousavi.foodino.repositories.MapRouteRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

import static com.developer.smmousavi.foodino.fragments.map.MapFragment.TAG;

public class MapFragmentViewModel extends BaseViewModel {

    @Inject
    public MapFragmentViewModel(MapRouteRestApi mapRouteRestApi) {
        mRouteRepo = MapRouteRepository.getInstance();
        if (mapRouteRestApi == null)
            Log.i(TAG, "MapFragmentViewModel: mapRouteRestApi is null");
        else
            Log.i(TAG, "MapFragmentViewModel: mapRouteRestApi is NOT null");
    }

    private MapRouteRepository mRouteRepo;

    public void getRoute(double[] origin, double[] dest) {
        mRouteRepo.getRoute(origin, dest);
    }

    public MutableLiveData<String> getRouteLd() {
        return mRouteRepo.getRouteLd();
    }

    public MutableLiveData<Boolean> getRouteFailedLd() {
        return mRouteRepo.getRouteQueryFailedLd();
    }

}
