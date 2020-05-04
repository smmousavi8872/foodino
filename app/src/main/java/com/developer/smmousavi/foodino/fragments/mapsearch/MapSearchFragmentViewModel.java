package com.developer.smmousavi.foodino.fragments.mapsearch;

import android.util.Log;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.network.api.MapSearchRestApi;
import com.developer.smmousavi.foodino.network.mapresponse.MapSearchBody;
import com.developer.smmousavi.foodino.network.mapresponse.Value;
import com.developer.smmousavi.foodino.repositories.MapSearchRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

import static com.developer.smmousavi.foodino.fragments.map.MapFragment.TAG;

public class MapSearchFragmentViewModel extends BaseViewModel {

    private MapSearchRepository mMapSearchRepo;

    @Inject
    public MapSearchFragmentViewModel(MapSearchRestApi mapSearchRestApi) {
        mMapSearchRepo = MapSearchRepository.getInstance();
        if (mapSearchRestApi == null)
            Log.i(TAG, "MapSearchFragmentViewModel: mapSearchRestApi is null");
        else
            Log.i(TAG, "MapSearchFragmentViewModel: mapSearchRestApi is NOT null");
    }

    public void searchMap(MapSearchBody body) {
        mMapSearchRepo.searchMap(body);
    }

    public MutableLiveData<List<Value>> getMapSearchLd() {
        return mMapSearchRepo.getMapSearchLd();
    }

    public MutableLiveData<Boolean> getMapSearchQueryFailedLd() {
        return mMapSearchRepo.getMapSearchQueryFailedLd();
    }
}
