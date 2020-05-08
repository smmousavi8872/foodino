package com.developer.smmousavi.foodino.ui.fragments.home;


import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.adapters.home.CategoryPreviewRvAdapter;
import com.developer.smmousavi.foodino.adapters.home.HomeSliderAdapter;
import com.developer.smmousavi.foodino.adapters.home.ProductPreviewRvAdapter;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;
import com.developer.smmousavi.foodino.models.Banner;
import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.network.util.Resource;
import com.developer.smmousavi.foodino.ui.activities.drawer.OnBackPressedListener;
import com.developer.smmousavi.foodino.ui.activities.drawer.SetOnContentFragmentInsert;
import com.developer.smmousavi.foodino.ui.activities.home.HomeDrawerActivity;
import com.developer.smmousavi.foodino.ui.customviews.CustomMapView;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.ui.fragments.chatbottomsheet.ChatBottomSheetFargment;
import com.developer.smmousavi.foodino.ui.fragments.chatbottomsheet.OnChatCloseClickListener;
import com.developer.smmousavi.foodino.util.TimerUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.map.sdk_map.MapirStyle;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeDrawerFragment extends BaseDaggerFragment implements OnBackPressedListener, OnChatCloseClickListener {

    public static final String TAG = "com.developer.smmousavi.digishop.fragment.main.HomeDrawerFragment";
    public static final String LOG_TAG = "LogTag";

    @BindView(R.id.productPreviewSlider)
    SliderView mSliderView;
    @BindView(R.id.rvCategoryPreview)
    RecyclerView mCategoryPreviewRv;
    @BindView(R.id.txtCountDownTimerHour)
    AppCompatTextView mTxtCountDownTimerHour;
    @BindView(R.id.txtCountDownTimerMin)
    AppCompatTextView mTxtCountDownTimerMin;
    @BindView(R.id.txtCountDownTimerSec)
    AppCompatTextView mTxtCountDownTimerSec;
    @BindView(R.id.rvProductPreview)
    RecyclerView mProductPreviewRv;
    @BindView(R.id.fabChat)
    FloatingActionButton mFabChat;
    @BindView(R.id.txtShopNumberTitle)
    AppCompatTextView mTxtChatFabBadgeTitle;
    @BindView(R.id.clChatBottomSheetContainer)
    CoordinatorLayout mClChatBottomSheetContainer;
    @BindView(R.id.flChatBottomSheetContainer)
    FrameLayout mFlChatBottomSheetContainer;
    @BindView(R.id.map_view)
    CustomMapView mMapView;


    private SliderViewAdapter mSliderAdapter;
    private List<Banner> mSliderBannerList;
    private List<Category> mCategoryPreviewList;
    private List<Product> mProductPreviewList;
    private CategoryPreviewRvAdapter mCategoryPreviewRvAdapter;
    private ProductPreviewRvAdapter mProductPreviewRvAdapter;
    private CountDownTimer mCountDownTimer;
    private BottomSheetBehavior mBottomSheetBehavior;
    private HomeFragmentViewModel mViewModel;
    MapboxMap mMap;
    Style mMapStyle;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    @Inject
    RecyclerViewHelper mRvHelper;

    public BottomSheetBehavior getBottomSheetBehavior() {
        return mBottomSheetBehavior;
    }

    public static HomeDrawerFragment newInstance() {

        Bundle args = new Bundle();

        HomeDrawerFragment fragment = new HomeDrawerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_drawer, container, false);
        ButterKnife.bind(this, v);

        initVariables();

        setImageSlider();

        setCategoryPreviewRv();

        initMap(savedInstanceState);

        subscribeObservers();

        setBottomSheet();

        return v;
    }


    private void initVariables() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(HomeFragmentViewModel.class);
        mSliderBannerList = mViewModel.getBannerListLiveData().getValue();
        mCategoryPreviewList = mViewModel.getCategoryPreviewListLiveData().getValue();
        mProductPreviewList = mViewModel.getProductPreviewListLiveData().getValue();
    }


    /**
     * @HardCoded TODO: should receive time from server
     */
    private void subscribeObservers() {
        mViewModel.searchIncredibleRecipes("beef", 1);
        mViewModel.getRecipeMDL().observe(this, new Observer<Resource<List<Recipe>>>() {
            @Override
            public void onChanged(Resource<List<Recipe>> listResource) {
                //onChange()
                if (listResource != null) {
                    switch (listResource.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            Log.d(LOG_TAG, "subscribeObserver: cache has been refreshed.");
                            Log.d(LOG_TAG, "subscribeObserver: status: SUCCESS, #recipes: " + listResource.data.size());
                            setSpecialOfferProductPreviewRv(listResource.data);
                            break;
                        case ERROR:
                            Log.e(LOG_TAG, "subscribeObserver: can not refresh the cache.");
                            Log.e(LOG_TAG, "subscribeObserver: Error message: " + listResource.message);
                            Log.e(LOG_TAG, "subscribeObserver: status: ERROR, #recipes: " + listResource.data.size());
                            setSpecialOfferProductPreviewRv(listResource.data);
                            break;
                    }
                }
            }
        });
    }

    private void setImageSlider() {
        mSliderAdapter = new HomeSliderAdapter(mSliderBannerList);
        mSliderView.setSliderAdapter(mSliderAdapter);
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        /*
            Set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or
            FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP
        */
        mSliderView.setIndicatorAnimation(IndicatorAnimations.COLOR);
        mSliderView.setCircularHandlerEnabled(true);
    }

    protected void setCategoryPreviewRv() {
        if (mCategoryPreviewRvAdapter == null) {
            mCategoryPreviewRvAdapter = new CategoryPreviewRvAdapter(mCategoryPreviewList);
        } else {
            mCategoryPreviewRvAdapter.setItemList(mCategoryPreviewList);
            mCategoryPreviewRvAdapter.notifyDataSetChanged();
        }

        LinearLayoutManager layoutManager = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.HORIZONTAL, true);
        mRvHelper.buildRecyclerView(layoutManager, mCategoryPreviewRv, mCategoryPreviewRvAdapter);
        /*
            These lines of code below are to solve the problem of recyclerview items when the parent layout is
            CardView and no margins are applied around the items
        */
        LayoutMarginDecoration marginDecoration = new LayoutMarginDecoration(1, 32);
        marginDecoration.setPadding(mCategoryPreviewRv, 4, 16, 32, 32);
        mCategoryPreviewRv.addItemDecoration(marginDecoration);
    }


    protected void setSpecialOfferProductPreviewRv(List<Recipe> recipes) {
        mProductPreviewRvAdapter = new ProductPreviewRvAdapter();
        mProductPreviewRvAdapter.setItemList(recipes);

        /*
            These lines of code below are to solve the problem of recyclerview items when the parent layout is
            CardView and no margins are applied around the items
        */
        LayoutMarginDecoration marginDecoration = new LayoutMarginDecoration(1, 24);
        marginDecoration.setPadding(mProductPreviewRv, 0, 0, 4, 4);
        mProductPreviewRv.addItemDecoration(marginDecoration);
        LinearLayoutManager layoutManager = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.HORIZONTAL, true);
        mRvHelper.buildRecyclerView(layoutManager, mProductPreviewRv, mProductPreviewRvAdapter);
    }


    private void startCountDownTimer(int h, int m, int s) {
        long timeMillis = TimerUtils.convertToTimeMillis(h, m, s);
        mCountDownTimer = new CountDownTimer(timeMillis, 1000) {

            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                long[] timeFormat = TimerUtils.convertToTimeFormat(millisUntilFinished);
                mTxtCountDownTimerHour.setText(String.format("%02d", timeFormat[0]));
                mTxtCountDownTimerMin.setText(String.format("%02d", timeFormat[1]));
                mTxtCountDownTimerSec.setText(String.format("%02d", timeFormat[2]));
            }

            public void onFinish() {
                //TODO: do whatever must be done when count down timer is finished.
            }
        }.start();
    }

    private void setBottomSheet() {
        ChatBottomSheetFargment.newInstance().setOnChatCloseClickListener(this);
        mBottomSheetBehavior = BottomSheetBehavior.from(mFlChatBottomSheetContainer);
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setSkipCollapsed(false);
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    private void initMap(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(mapboxMap -> {
            mMap = mapboxMap;
            mMap.setStyle(new Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE), new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    mMapStyle = style;
                    getLocationAccess();
                }
            });
        });
    }

    private void getLocationAccess() {
        Dexter.withActivity(this.getActivity()).
            withPermission(Manifest.permission.ACCESS_FINE_LOCATION).
            withListener(new PermissionListener() {
                @Override
                public void onPermissionGranted(PermissionGrantedResponse response) {
                    showCurrentLocation();
                    moveCameraToCurrentPosition();
                }

                @Override
                public void onPermissionDenied(PermissionDeniedResponse response) {
                }

                @Override
                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                }
            }).check();
    }

    private void showCurrentLocation() {
        LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(getContext())
            .elevation(10)
            .accuracyAlpha(.5f)
            .accuracyColor(Color.BLUE)
            .build();
        // Get an instance of the component
        LocationComponent locationComponent = mMap.getLocationComponent();

        LocationComponentActivationOptions locationComponentActivationOptions =
            LocationComponentActivationOptions.builder(getContext(), mMapStyle)
                .locationComponentOptions(customLocationComponentOptions)
                .build();
        // Activate with options
        locationComponent.activateLocationComponent(locationComponentActivationOptions);
        // Enable to make component visible
        locationComponent.setLocationComponentEnabled(true);
        // Set the component's camera mode
        locationComponent.setCameraMode(CameraMode.TRACKING);
        // Set the component's render mode
        locationComponent.setRenderMode(RenderMode.COMPASS);
        // Add the location icon click listener
        locationComponent.addOnLocationClickListener(() -> {
        });
    }

    private void moveCameraToCurrentPosition() {
        Location location = mMap.getLocationComponent().getLastKnownLocation();
        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                .zoom(12)                   // Sets the zoom
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mCountDownTimer.cancel();
    }

    @OnClick(R.id.fabChat)
    public void setOnFabChatListener() {
        HomeDrawerActivity parent = (HomeDrawerActivity) getActivity();
        parent.insertContentFragment(new SetOnContentFragmentInsert() {
            @Override
            public int getFragmentId() {
                return R.id.flChatBottomSheetContainer;
            }

            @Override
            public Fragment getFragmentObject() {
                return ChatBottomSheetFargment.newInstance();
            }

            @Override
            public String getFragmentTag() {
                return ChatBottomSheetFargment.TAG;
            }
        });
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        parent.setOnBackPressedListener(this);
    }

    @Override
    public void onBack() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onCloseClicked() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }


    /*
     * Override Region
     */
    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    /**
     * @HardCoded TODO: should receive time from server
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
        startCountDownTimer(14, 1, 30);
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMapView.onDestroy();
    }

}
