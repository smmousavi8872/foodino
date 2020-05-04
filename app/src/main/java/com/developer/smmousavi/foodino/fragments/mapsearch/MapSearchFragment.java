package com.developer.smmousavi.foodino.fragments.mapsearch;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.adapters.mapsearch.MapSearchRvAdapter;
import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.fragments.map.MapFragment;
import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;
import com.developer.smmousavi.foodino.manager.KeyboardManager;
import com.developer.smmousavi.foodino.network.mapresponse.MapSearchBody;
import com.developer.smmousavi.foodino.util.Animations;
import com.victor.loading.rotate.RotateLoading;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapSearchFragment extends BaseDaggerFragment implements AddressSelectCallback {

    public static final String TAG = "SearchMapFragmentTag";

    @BindView(R.id.svSearchMap)
    SearchView mSvSearchMap;
    @BindView(R.id.prgSearchMap)
    RotateLoading mPrgSearchMap;
    @BindView(R.id.imgSearchBack)
    AppCompatImageView mSearchBack;
    @BindView(R.id.rvSearchResult)
    RecyclerView mSearchResultRv;

    private MapSearchFragmentViewModel mViewModel;
    private KeyboardManager mKeyboardManager;
    private MapSearchRvAdapter mMapSearchRvAdapter;
    private boolean mIsKeyboardVisible;

    @Inject
    RecyclerViewHelper mRvHelper;
    @Inject
    ViewModelProviderFactory mProviderFactory;

    public MapSearchFragment() {
        // Required empty public constructor
    }


    public static MapSearchFragment newInstance() {
        MapSearchFragment fragment = new MapSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_map, container, false);
        ButterKnife.bind(this, view);

        initViewModel();

        initKeyboard();

        initSearchView();

        initRecipeRv();

        subscribeObservers();

        return view;
    }


    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(MapSearchFragmentViewModel.class);
    }


    private void subscribeObservers() {
        mViewModel.getMapSearchLd().observe(this, mapSearchResponse -> {
            mMapSearchRvAdapter.setItemList(mapSearchResponse);
            mPrgSearchMap.stop();
        });
    }


    private void initKeyboard() {
        mKeyboardManager = new KeyboardManager(getContext());
        mKeyboardManager.registerKeyboardListener(new KeyboardManager.OnKeyboardListener() {
            @Override
            public void onKeyboardVisible() {
                mIsKeyboardVisible = true;
            }

            @Override
            public void onKeyboardHidden() {
                mIsKeyboardVisible = false;
            }
        }, mSvSearchMap);

        new Handler().postDelayed(() -> {
            mKeyboardManager.show(mSvSearchMap);
        }, 200);
    }


    private void initSearchView() {
        mSvSearchMap.setQueryHint(getContext().getResources().getString(R.string.hint_search_map));
        mSvSearchMap.setFocusable(true);
        mSvSearchMap.setIconified(false);
        mSvSearchMap.requestFocusFromTouch();
        mSvSearchMap.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mPrgSearchMap.start();
                MapSearchBody body = getMapSearchBody(s);
                mViewModel.searchMap(body);
                return false;
            }
        });
    }


    private void initRecipeRv() {
        mMapSearchRvAdapter = new MapSearchRvAdapter(this);
        LinearLayoutManager layoutManager = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.VERTICAL, false);
        mRvHelper.buildRecyclerView(layoutManager, mSearchResultRv, mMapSearchRvAdapter);
    }


    private MapSearchBody getMapSearchBody(String query) {
        return new MapSearchBody(query, "nearby", Constants.TEHRAN_LNG, Constants.TEHRAN_LAT);
    }


    private void finishFragment() {
        mSvSearchMap.clearFocus();
        mPrgSearchMap.stop();
        mMapSearchRvAdapter.clear();
    }


    @OnClick(R.id.imgSearchBack)
    public void onSearchBackClick() {
        replaceFragment(R.id.flSingleFragmentContainer, MapFragment.newInstance(null),
            MapFragment.TAG, Animations.SLIDE_DOWN, Animations.SLIDE_IN_BOTTOM_FAST);
        finishFragment();
    }


    @Override
    public void onAddressSelected(double[] latLng) {
        replaceFragment(R.id.flSingleFragmentContainer, MapFragment.newInstance(latLng),
            MapFragment.TAG, Animations.SLIDE_DOWN, Animations.SLIDE_IN_BOTTOM_FAST);
        finishFragment();
    }
}
