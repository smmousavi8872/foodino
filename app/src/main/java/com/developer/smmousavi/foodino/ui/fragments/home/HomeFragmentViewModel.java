package com.developer.smmousavi.foodino.ui.fragments.home;

import android.app.Application;
import android.util.Log;

import com.developer.smmousavi.foodino.base.BaseViewModel;
import com.developer.smmousavi.foodino.models.Banner;
import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.util.Resource;
import com.developer.smmousavi.foodino.repositories.HomeRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import static com.developer.smmousavi.foodino.ui.fragments.productdetail.ProductDetailFragment.TAG;

public class HomeFragmentViewModel extends BaseViewModel {

    private static final String QUERY_EXHAUSTED = "";
    private HomeRepository mHomeRepository;
    private MediatorLiveData<Resource<List<Recipe>>> mRecipeMDL = new MediatorLiveData<>();
    private String mQuery;
    private int mPageNumber;
    private boolean mIsPerformingQuery;
    private boolean mIsQueryExhausted;

    public MediatorLiveData<Resource<List<Recipe>>> getRecipeMDL() {
        return mRecipeMDL;
    }

    @Inject
    public HomeFragmentViewModel(Application app) {
        super(app);
        mHomeRepository = HomeRepository.getInstance(app);
    }

    public void searchIncredibleRecipes(String query, int pageNum) {
        searchRecipesApi(query, pageNum);
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (!mIsPerformingQuery) {
            if (pageNumber == 0) {
                pageNumber = 1;
            }
            this.mPageNumber = pageNumber;
            this.mQuery = query;
            mIsQueryExhausted = false;
            executeSearch();
        }
    }

    public void executeSearch() {
        final LiveData<Resource<List<Recipe>>> repoSource = mHomeRepository.getIncredibleRecipes(mQuery, mPageNumber);
        mRecipeMDL.addSource(repoSource, listResource -> {
            //onChange
            if (listResource != null) {
                mRecipeMDL.setValue(listResource);
                if (listResource.status == Resource.Status.SUCCESS) {
                    mIsPerformingQuery = false;
                    if (listResource.data != null) {
                        if (listResource.data.size() % 30 != 0) {
                            Log.d(TAG, "executeSearch: query is exhausted.");
                            mRecipeMDL.setValue(new Resource<>(Resource.Status.ERROR, listResource.data, QUERY_EXHAUSTED));
                        }
                    }
                    mRecipeMDL.removeSource(repoSource);
                } else if (listResource.status == Resource.Status.ERROR) {
                    mIsPerformingQuery = false;
                    mRecipeMDL.removeSource(repoSource);
                }
            } else {
                mRecipeMDL.removeSource(repoSource);
            }
        });
    }

    public LiveData<List<Banner>> getBannerListLiveData() {
        return mHomeRepository.getSliderBannerList();
    }

    public LiveData<List<Category>> getCategoryPreviewListLiveData() {
        return mHomeRepository.getCategoryPreviewList();
    }

    public LiveData<List<Product>> getProductPreviewListLiveData() {
        return mHomeRepository.getProductPreviewList();
    }

}
