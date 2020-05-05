package com.developer.smmousavi.foodino.fragments.map;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.customviews.CustomMapView;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.fragments.mapsearch.MapSearchFragment;
import com.developer.smmousavi.foodino.util.Animations;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
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
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.map.sdk_map.MapirStyle;

import static com.mapbox.mapboxsdk.style.layers.Property.LINE_JOIN_BEVEL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseDaggerFragment {

    private static final String LOG_TAG = "MapFragment";
    public static final String TAG = "MapFragmentTag";
    public static final String ARGS_DOUBLE_ARR_LAT_LNG = "ArgsDoubleArrLatLng";
    public static final int ZOOM_LEVEL_FAR = 12;
    public static final int ZOOM_LEVEL_MIDDLE = 14;
    public static final int ZOOM_LEVEL_CLOSE = 17;
    public static final int DELAY_MILLIS = 300;


    @BindView(R.id.map_view)
    CustomMapView mMapView;
    @BindView(R.id.fabCurrentLocation)
    FloatingActionButton mFabCurrentLocation;
    @BindView(R.id.fabDirection)
    FloatingActionButton mFabDirection;
    @BindView(R.id.cvSearchMap)
    MaterialCardView mCvSearchMap;
    @BindView(R.id.txtSearchHear)
    AppCompatTextView mTxtSearchHear;

    MapboxMap mMap;
    Style mMapStyle;

    private MapFragmentViewModel mViewModel;
    private String mPinSourceId = "";
    private String mPinImageId = "";
    private String mPinLayerId = "";
    private String mLineSourceId = "";
    private String mLineLayerId = "";
    private boolean mPinAdded;
    private boolean mRouteAdded;
    private boolean mRouteIconVisible = true;
    private LatLng mPinLatLng;
    private LatLng mCurrentLatLng;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    public static MapFragment newInstance(@Nullable double[] latLng) {
        Bundle args = new Bundle();
        args.putDoubleArray(ARGS_DOUBLE_ARR_LAT_LNG, latLng);

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(MapFragmentViewModel.class);

        subscribeObservers();

        new Handler().postDelayed(() -> initMap(savedInstanceState), DELAY_MILLIS);

        return view;
    }


    private void subscribeObservers() {
        mViewModel.getRouteLd().observe(this, route -> {
            if (mMapStyle != null && mMapStyle.isFullyLoaded() && mMapStyle.getSource(mLineSourceId) == null)
                showRouteOnMap(route);
        });
    }


    private void initMap(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(mapboxMap -> {
            // onMapReady()
            mMap = mapboxMap;
            mMap.setStyle(new Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE), style -> {
                // onStyleLoaded()
                mMapStyle = style;
                getLocationAccess();

                mMap.addOnMapLongClickListener(point -> {
                    mPinLatLng = point;
                    setPinOnMap();
                    return true;
                });
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
                    if (getArguments().getDoubleArray(ARGS_DOUBLE_ARR_LAT_LNG) == null) {
                        zoom(mCurrentLatLng, ZOOM_LEVEL_MIDDLE);
                    } else
                        initSelectedAddress();
                }

                @Override
                public void onPermissionDenied(PermissionDeniedResponse response) {
                }

                @Override
                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                }
            }).check();
    }


    private void initSelectedAddress() {
        double[] latLng = getArguments().getDoubleArray(ARGS_DOUBLE_ARR_LAT_LNG);
        mPinLatLng = new LatLng();
        mPinLatLng.setLongitude(latLng[0]);
        mPinLatLng.setLatitude(latLng[1]);
        setPinOnMap();
        zoom(mPinLatLng, ZOOM_LEVEL_CLOSE);
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
        updateCurrentLocation();
    }

    private void updateCurrentLocation() {
        LocationComponent locationComponent = mMap.getLocationComponent();
        Location location = locationComponent.getLastKnownLocation();
        if (location != null)
            mCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    private void setPinOnMap() {
        // Add source to map
        if (mPinAdded)
            removePin();
        List<Feature> samplePointsFeatures = new ArrayList<>();
        Feature sampleFeature = Feature.fromGeometry(Point.fromLngLat(mPinLatLng.getLongitude(), mPinLatLng.getLatitude()));
        samplePointsFeatures.add(sampleFeature);
        FeatureCollection featureCollection = FeatureCollection.fromFeatures(samplePointsFeatures);
        mPinSourceId = "source_id" + mPinLatLng.getLongitude() + "" + mPinLatLng.getLatitude();
        mPinImageId = "image_id" + mPinLatLng.getLongitude() + "" + mPinLatLng.getLatitude();
        mPinLayerId = "layer_id" + mPinLatLng.getLongitude() + "" + mPinLatLng.getLatitude();
        GeoJsonSource geoJsonSource = new GeoJsonSource(mPinSourceId, featureCollection);
        mMapStyle.addSource(geoJsonSource);
        // Add image to map
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.mapbox_marker_icon_default);
        mMapStyle.addImage(mPinImageId, icon);
        // Add layer to map
        SymbolLayer symbolLayer = new SymbolLayer(mPinLayerId, mPinSourceId);
        symbolLayer.setProperties(
            PropertyFactory.iconImage(mPinImageId),
            PropertyFactory.iconSize(1.5f),
            PropertyFactory.iconOpacity(.8f),
            PropertyFactory.textColor("#ff5252")
        );

        mMapStyle.addLayer(symbolLayer);
        mPinAdded = true;
        String coordinates = String.format("%s ,%s", mPinLatLng.getLatitude(), mPinLatLng.getLongitude());
        mTxtSearchHear.setText(coordinates);
        if (!mFabDirection.isShown()) {
            showFab(mFabDirection);
        }
    }

    private void moveCameraToPosition(LatLng latLng, int zoomLevel) {
        if (latLng != null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to location user
                .zoom(zoomLevel)                   // Sets the zoom
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    private void zoom(LatLng zoomPoint, int zoomLevel) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zoomPoint, zoomLevel));
    }

    private void showRouteOnMap(String geometry) {
        // Create the LineString from the list of coordinates
        LineString lineString = LineString.fromPolyline(geometry, 5);
        FeatureCollection featureCollection = FeatureCollection.fromFeature(Feature.fromGeometry(lineString));
        mLineSourceId = "line_id:" + geometry;
        mLineLayerId = "layer_id:" + geometry;
        GeoJsonSource geoJsonSource = new GeoJsonSource(mLineSourceId, featureCollection);
        mMapStyle.addSource(geoJsonSource);
        // Add layer to map
        LineLayer lineLayer = new LineLayer(mLineLayerId, mLineSourceId);
        lineLayer.setProperties(
            PropertyFactory.lineWidth(5f),
            PropertyFactory.lineGapWidth(1f),
            PropertyFactory.lineColor("#607FDC"),
            PropertyFactory.lineJoin(LINE_JOIN_BEVEL),
            PropertyFactory.lineDasharray(new Float[]{5f, 2f})
        );
        mMapStyle.addLayer(lineLayer);
        zoom(mPinLatLng, ZOOM_LEVEL_FAR);
        mRouteAdded = true;
    }

    private void removePin() {
        if (mRouteAdded) {
            mMapStyle.removeLayer(mLineLayerId);
            mMapStyle.removeSource(mLineSourceId);
            animateFabIcon(mFabDirection);
            mRouteAdded = false;
            mRouteIconVisible = false;
        }
        mPinAdded = false;
        mMapStyle.removeLayer(mPinLayerId);
        mTxtSearchHear.setText(R.string.hint_search_here);
    }

    private void hideFab(FloatingActionButton fab) {
        fab.hide();
    }

    private void showFab(FloatingActionButton fab) {
        fab.show();
    }

    private void animateFabIcon(FloatingActionButton fab) {
        ObjectAnimator.ofFloat(fab, "rotation", 0f, 360f).setDuration(600).start();
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (!mRouteIconVisible) {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_directions_primary_24dp));
                mRouteIconVisible = true;
            } else {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_primery_24dp));
                mRouteIconVisible = false;
            }
        }, DELAY_MILLIS);
    }

    @NonNull
    private LatLng getTehranLatLng() {
        LatLng latLng = new LatLng();
        latLng.setLongitude(Constants.TEHRAN_LNG);
        latLng.setLatitude(Constants.TEHRAN_LAT);
        return latLng;
    }

    /*
     * Onclick Region
     */
    @OnClick(R.id.fabCurrentLocation)
    void setOnCurrentFabClick() {
        moveCameraToPosition(mCurrentLatLng, ZOOM_LEVEL_CLOSE);
    }

    @OnClick(R.id.fabDirection)
    void setOnDirectionFabClick() {
        if (mRouteIconVisible) {
            double[] origin = new double[2];
            double[] dest = new double[2];
            origin[0] = mCurrentLatLng.getLongitude();
            origin[1] = mCurrentLatLng.getLatitude();
            dest[0] = mPinLatLng.getLongitude();
            dest[1] = mPinLatLng.getLatitude();
            mViewModel.getRoute(origin, dest);
            animateFabIcon(mFabDirection);
        } else {
            removePin();
            hideFab(mFabDirection);

        }
    }

    @OnClick(R.id.cvSearchMap)
    void setOnSearchMapClick() {
        replaceFragment(R.id.flSingleFragmentContainer, MapSearchFragment.newInstance(),
            MapSearchFragment.TAG, Animations.SLIDE_IN_TOP_FAST, Animations.SLIDE_UP, true);
    }

    /*
     * Override Region
     */
    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
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