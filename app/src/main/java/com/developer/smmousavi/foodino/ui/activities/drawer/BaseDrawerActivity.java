package com.developer.smmousavi.foodino.ui.activities.drawer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.activities.aboutus.AboutUsActivity;
import com.developer.smmousavi.foodino.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmousavi.foodino.ui.activities.categorylist.CategoryListActivity;
import com.developer.smmousavi.foodino.ui.activities.map.MapActivity;
import com.developer.smmousavi.foodino.ui.activities.home.HomeDrawerActivity;
import com.developer.smmousavi.foodino.ui.activities.settings.SettingsActivity;
import com.developer.smmousavi.foodino.ui.activities.shoppingbasket.ShoppingBasketActivity;
import com.developer.smmousavi.foodino.ui.activities.signinsignup.SignInSignUpActivity;
import com.developer.smmousavi.foodino.ui.activities.specialoffers.SpecialOffersActivity;
import com.developer.smmousavi.foodino.ui.activities.specialoffers.SpecialOffersActivity.OfferType;
import com.developer.smmousavi.foodino.ui.fragments.home.HomeDrawerFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseDrawerActivity extends BaseDaggerCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, OnBackPressedListener, SetOnContentFragmentInsert, SetOnToolbarVisibility {

    @BindView(R.id.txtShopNumberTitle)
    AppCompatTextView mTxtShoppingBasketBadge;
    @BindView(R.id.imgToolbarSearch)
    AppCompatImageView mImgToolbarSearch;
    @BindView(R.id.imgToolbarShoppingBasket)
    AppCompatImageView mImgToolbarShoppingBasket;
    @BindView(R.id.imgToolbarNavbarButton)
    AppCompatImageView mImgToolbarNavbar;
    @BindView(R.id.navbarView)
    NavigationView mNavigationView;
    @BindView(R.id.dlMainFragmentDrawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.includeToolbar)
    AppBarLayout mToolabrLayout;

    private AppCompatTextView mTxtSignupButton;
    private OnBackPressedListener mOnBackPressedListener;

    public NavigationView getNavigationView() {
        return mNavigationView;
    }

    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mOnBackPressedListener = listener;
    }

    @Override
    protected void onResume() {
        super.onResume();
        uncheckNavBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawer);
        ButterKnife.bind(this);

        setStateBarColor();

        insertContentFragment(this);

        initNavView();

        initToolbar();
    }

    private void setStateBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow()
                .setNavigationBarColor(ContextCompat.getColor(this, R.color.pureWhite));
            //status bar or the time bar at the top
            getWindow()
                .setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
    }


    private void initToolbar() {
        if (!isToolbarVisible())
            mToolabrLayout.setVisibility(View.GONE);
        //TODO: should set the shopping basket count to mTxtShoppingBasketBadge, using SharedPrefUtils
        //should implement search icon functionality in toolbar
    }


    private void initNavView() {
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.getMenu()
            .getItem(0)
            .setChecked(true);

        // to set listener on views of header, first shoud find them
        // here is how to get NavigationView header
        View navHeader = mNavigationView.getHeaderView(0);
        mTxtSignupButton = navHeader.findViewById(R.id.txtNavbarSignUpButton);
        mTxtSignupButton.setOnClickListener(v -> {
            startActivity(SignInSignUpActivity.newIntent(this));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            new Handler().postDelayed(this::closeDrawer, 600);
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        setNavigationItemAction(item);

        //new Handler().postDelayed(this::closeDrawer, 150);
        return true;
    }

    private void setNavigationItemAction(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navbarMenuHome:
                if (!(getFragmentObject() instanceof HomeDrawerFragment)) {
                    intent = HomeDrawerActivity.newIntent(this);
                    // to finish all previous activities
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                } else
                    intent = null;
                break;
            case R.id.navbarMenuCatList:
                intent = CategoryListActivity.newIntent(this, 0);
                break;
            case R.id.navbarMenuShoppingBasket:
                intent = ShoppingBasketActivity.newIntent(this);
                break;
            case R.id.navbarMenuSpecialOffer:
                intent = SpecialOffersActivity.newIntent(this, OfferType.SPECIAL_OFFER);
                break;
            case R.id.navbarMenuMostSold:
                intent = SpecialOffersActivity.newIntent(this, OfferType.MOST_SOLD);
                break;
            case R.id.navbarMenuMostSeen:
                intent = SpecialOffersActivity.newIntent(this, OfferType.MOST_SEEN);
                break;
            case R.id.navbarMenuNewest:
                intent = SpecialOffersActivity.newIntent(this, OfferType.NEWEST);
                break;
            case R.id.navbarMenuSettings:
                intent = SettingsActivity.newIntent(this);
                break;
            case R.id.navbarMap:
                intent = MapActivity.newIntent(this);
                break;
            case R.id.navbarMenuAboutUs:
                intent = AboutUsActivity.newIntent(this);
                break;
            default:
                intent = HomeDrawerActivity.newIntent(this);
                break;
        }
        closeDrawer();
        if (intent != null)
            startActivity(intent);
        new Handler().postDelayed(() -> {
        }, 200);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.END);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.END);
    }

    public void uncheckNavBar() {
        int itemCount = mNavigationView.getMenu().size();
        for (int i = 0; i < itemCount; i++) {
            if (mNavigationView.getMenu().getItem(i).isChecked()) {
                mNavigationView.getMenu().getItem(i).setChecked(false);
            }
        }
    }


    public void insertContentFragment(SetOnContentFragmentInsert contentSet) {
        int containerId = contentSet.getFragmentId();
        Fragment fragmentObject = contentSet.getFragmentObject();
        String fragmentTag = contentSet.getFragmentTag();

        Fragment foundFragment = mFm.findFragmentById(containerId);

        if (foundFragment == null) {
            mFm.beginTransaction()
                .add(containerId, fragmentObject, fragmentTag)
                .commit();
        }
    }

    @OnClick(R.id.imgToolbarNavbarButton)
    void setNavBarListener() {
        HomeDrawerFragment homeDrawerFragment = (HomeDrawerFragment) mFm.findFragmentByTag(HomeDrawerFragment.TAG);
        if (homeDrawerFragment != null)
            if (homeDrawerFragment.getBottomSheetBehavior().getState() == BottomSheetBehavior.STATE_EXPANDED)
                homeDrawerFragment.getBottomSheetBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);
        mDrawerLayout.openDrawer(GravityCompat.END);
        setOnBackPressedListener(this);
    }

    @OnClick(R.id.imgToolbarShoppingBasket)
    public void setToolbarShoppingBasketListener(View v) {
        startActivity(ShoppingBasketActivity.newIntent(this));
    }


    @Override
    public void onBackPressed() {
        //Dirty Code
        //Find a better solution
        HomeDrawerFragment homeDrawerFragment = (HomeDrawerFragment) getSupportFragmentManager().findFragmentByTag(HomeDrawerFragment.TAG);
        if (mOnBackPressedListener != null) {
            if (isDrawerOpen() || homeDrawerFragment.getBottomSheetBehavior().getState() == BottomSheetBehavior.STATE_EXPANDED)
                mOnBackPressedListener.onBack();
            else
                super.onBackPressed();
            mOnBackPressedListener = null;
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_navbar, menu);
        return true;
    }


    @Override
    public void onBack() {
        closeDrawer();

    }
}

