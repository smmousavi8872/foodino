package com.developer.smmousavi.foodino.network.util;

import android.util.Log;

import com.developer.smmousavi.foodino.network.AppExecutors;
import com.developer.smmousavi.foodino.network.reciperesponses.ApiResponse;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

// CacheObj : Type for the Resource data. (database cache)
// RequestObj: Type for the API response. (network request)
public abstract class NetworkBoundResource<CacheObj, RequestObj> {

    private static final String TAG = "NetworkBoundResource";

    private AppExecutors mAppExecutors;
    private MediatorLiveData<Resource<CacheObj>> results = new MediatorLiveData<>();

    public NetworkBoundResource(AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
        init();
    }

    private void init() {
        //update LiveData for loading status
        results.setValue(Resource.loading(null));

        //observer LiveData source from local db
        final LiveData<CacheObj> dbSource = loadFromDb();

        results.addSource(dbSource, cacheObj -> {
            results.removeSource(dbSource);
            if (shouldFetch(cacheObj)) {
                // get data from the network
                fetchFromNetwork(dbSource);
            } else {
                // view data from cache
                results.addSource(dbSource, cacheObj1 -> {
                    setValue(Resource.success(cacheObj1));
                });
            }
        });
    }

    /**
     * 1) observe local db
     * 2) if <condition/> query the network
     * 3) stop observing the local db
     * 4) insert new data into local db
     * 5) begin observing local db again to see the refreshed data from network
     *
     * @param dbSource
     */
    private void fetchFromNetwork(final LiveData<CacheObj> dbSource) {

        Log.d(TAG, "fetchFromNetwork: called.");
        // update LiveData for loading status
        results.addSource(dbSource, cacheObj -> {
            setValue(Resource.loading(cacheObj));

        });

        final LiveData<ApiResponse<RequestObj>> apiResponse = createCall();

        results.addSource(apiResponse, requestObjApiResponse -> {
            results.removeSource(dbSource);
            results.removeSource(apiResponse);
            /*
                3 cases:
                    1) ApiSuccessResponse
                    2) ApiErrorResponse
                    3) ApiEmptyResponse
             */
            if (requestObjApiResponse instanceof ApiResponse.ApiSuccessResponse) {
                Log.d(TAG, "onChange: ApiSuccessResponse");
                mAppExecutors.diskIO().execute(() -> {
                    // save the response to the local db
                    saveCallResult((RequestObj) processResponse((ApiResponse.ApiSuccessResponse) requestObjApiResponse));
                    mAppExecutors.mainThread().execute(() -> {
                        results.addSource(loadFromDb(), cacheObj -> {
                            setValue(Resource.success(cacheObj));
                        });
                    });
                });

            } else if (requestObjApiResponse instanceof ApiResponse.ApiEmptyResponse) {
                Log.d(TAG, "fetchFromNetwork: ApiEmptyResponse");
                mAppExecutors.mainThread().execute(() -> {
                    results.addSource(loadFromDb(), cacheObj -> {
                        setValue(Resource.success(cacheObj));
                    });
                });

            } else if (requestObjApiResponse instanceof ApiResponse.ApiErrorResponse) {
                Log.d(TAG, "fetchFromNetwork: ApiErrorResponse");
                results.addSource(dbSource, cacheObj -> {
                    setValue(Resource.error(((ApiResponse.ApiErrorResponse) requestObjApiResponse).getErrorMsg(), cacheObj));
                });
            }
        });
    }

    private CacheObj processResponse(ApiResponse.ApiSuccessResponse response) {
        return (CacheObj) response.getBody();
    }

    private void setValue(Resource<CacheObj> newValue) {
        if (results.getValue() != newValue) {
            results.setValue(newValue);
        }

    }

    // Called to save the results of the API response into the database.
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestObj item);

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract boolean shouldFetch(@Nullable CacheObj data);

    // Called to get the cached data from the database.
    @NonNull
    @MainThread
    protected abstract LiveData<CacheObj> loadFromDb();

    // called to create the API call.
    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestObj>> createCall();

    // returns a LiveData object that represents the resource that's implemented
    // in the base class.
    public final LiveData<Resource<CacheObj>> getAsLiveData() {
        return results;
    }
}
