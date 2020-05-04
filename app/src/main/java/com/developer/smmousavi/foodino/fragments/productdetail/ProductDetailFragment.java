package com.developer.smmousavi.foodino.fragments.productdetail;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.activities.shoppingbasket.ShoppingBasketActivity;
import com.developer.smmousavi.foodino.adapters.productdetail.ProductDetailSliderAdapter;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.util.Animations;
import com.developer.smmousavi.foodino.util.TimerUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends BaseDaggerFragment {

    @BindView(R.id.appBarProductDetailHeader)
    AppBarLayout mProductDeatialHeader;
    @BindView(R.id.srlProductDetailInfoContainer)
    NestedScrollView mProductDetailInfoContainer;
    @BindView(R.id.prgProductDetail)
    RotateLoading mLoading;
    @BindView(R.id.llErrorConnectionContainer)
    LinearLayout mLlErrorConnnectionContainer;
    @BindView(R.id.productDetailSlider)
    SliderView mSliderView;
    @BindView(R.id.txtProductDetailTitle)
    AppCompatTextView mTxtProductDetailTitle;
    @BindView(R.id.txtCountDownTimerHour)
    AppCompatTextView mTxtCountDownTimerHour;
    @BindView(R.id.txtCountDownTimerMin)
    AppCompatTextView mTxtCountDownTimerMin;
    @BindView(R.id.txtCountDownTimerSec)
    AppCompatTextView mTxtCountDownTimerSec;
    @BindView(R.id.imgToolbarNavbarButton)
    AppCompatImageView mImgBack;


    private CountDownTimer mCountDownTimer;
    private SliderViewAdapter mSliderAdapter;
    private ProductDetailFragmentViewModel mViewModle;
    private String mRcipeId;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    public static final String TAG = "ProductDetailTag";

    public static final String ARGS_RECIPE_ID = "argsProductId";

    public static ProductDetailFragment newInstance(String recipeId) {

        Bundle args = new Bundle();
        args.putString(ARGS_RECIPE_ID, recipeId);

        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    /**
     * @HardCoded TODO: should recive time from server
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
        startCountDownTimer(14, 1, 30);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_detail_drawer, container, false);
        ButterKnife.bind(this, v);

        initVariable();

        subscribeRecipeObservers();

        return v;
    }

    private void initVariable() {
        mLoading.start();
        mRcipeId = getArguments().getString(ARGS_RECIPE_ID);
        mViewModle = ViewModelProviders.of(this, mProviderFactory).get(ProductDetailFragmentViewModel.class);
    }

    private void subscribeRecipeObservers() {
        mViewModle.getRecipe(mRcipeId);
        mViewModle.getRecipeLd().observe(this, recipe -> {
            if (recipe != null)
                if (recipe.getRecipeId().equals(mViewModle.getRecipeId())) {
                    Log.i("SUBSCRIE_OBSERVER", "recipe recieved");
                    setImageSlider(recipe);
                    setRecipeInfo(recipe);
                    setConnectionErrorView(false);
                }
        });
        mViewModle.getRecipeRequestFailedLd().observe(this, isRequestFaild -> {
            if (isRequestFaild) {
                Log.i("SUBSCRIE_OBSERVER", "connection faild");
                setConnectionErrorView(true);
            }
        });
    }

    /**
     * @HardCoded TODO: should recieve a list of urls from server
     */
    private void setImageSlider(Recipe recipe) {
        List<String> recipeImageUrls = new ArrayList<>();
        recipeImageUrls.add(recipe.getImageUrl());
        recipeImageUrls.add(recipe.getImageUrl());
        recipeImageUrls.add(recipe.getImageUrl());
        recipeImageUrls.add(recipe.getImageUrl());
        recipeImageUrls.add(recipe.getImageUrl());
        mSliderAdapter = new ProductDetailSliderAdapter(recipeImageUrls);
        mSliderView.setSliderAdapter(mSliderAdapter);
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        //Set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or
        //FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP
        mSliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        mSliderView.setCircularHandlerEnabled(true);
    }

    private void setRecipeInfo(Recipe recipe) {
        mTxtProductDetailTitle.setText(recipe.getTitle());
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

    private void setConnectionErrorView(boolean connectionError) {
        if (!connectionError) {
            mLlErrorConnnectionContainer.setVisibility(View.INVISIBLE);
            mProductDeatialHeader.setVisibility(View.VISIBLE);
            mProductDetailInfoContainer.setVisibility(View.VISIBLE);
            Animations.setAnimation(R.anim.fade_in, mProductDetailInfoContainer, mProductDeatialHeader);
            Log.i(TAG, "setConnectionErrorView: connection error.");
        } else {
            mProductDeatialHeader.setVisibility(View.INVISIBLE);
            mProductDetailInfoContainer.setVisibility(View.INVISIBLE);
            mLlErrorConnnectionContainer.setVisibility(View.VISIBLE);
            Log.i(TAG, "setConnectionErrorView: no connection error.");
        }
        mLoading.stop();
    }

    @OnClick(R.id.imgToolbarNavbarButton)
    public void OnToolbarBackListener() {
        getActivity().finish();
    }

    @OnClick(R.id.imgToolbarShoppingBasket)
    public void OnToolbarShoppingBasketListener() {
        Intent intent = ShoppingBasketActivity.newIntent(getActivity());
        getActivity().startActivity(intent);
    }
}
