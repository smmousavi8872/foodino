package com.developer.smmousavi.foodino.fragments.specialoffers;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.activities.shoppingbasket.ShoppingBasketActivity;
import com.developer.smmousavi.foodino.activities.specialoffers.SpecialOffersActivity.OfferType;
import com.developer.smmousavi.foodino.adapters.specialoffers.SpecialOffersRvAdapter;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;
import com.developer.smmousavi.foodino.util.Testing;
import com.victor.loading.rotate.RotateLoading;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOffersFragment extends BaseDaggerFragment {

    public static final String TAG = "com.developer.smmousavi.digishop.fragment.specialoffers.SpecialOffersFragment";
    public static final String LOG_TAG = "logTag";

    public static final String ARGS_OFFER_TYPE = "argsOfferType";


    private OfferType mOfferType;

    @BindView(R.id.txtToolbarTitle)
    AppCompatTextView mTxtToolbarTitle;
    @BindView(R.id.imgToolbarBackArrow)
    AppCompatImageView mImgToolbarBack;
    @BindView(R.id.imgToolbarSearch)
    AppCompatImageView mImgToolbarSearch;
    @BindView(R.id.imgToolbarShoppingBasket)
    AppCompatImageView mImgToolbarShoppingBasket;
    @BindView(R.id.rvSpecialOffersProducts)
    RecyclerView mSpecialOffersRv;
    @BindView(R.id.prgSpecialProductLoading)
    RotateLoading mPrgLoading;

    @Inject
    RecyclerViewHelper mRvHelper;
    @Inject
    ViewModelProviderFactory mProviderFactory;

    private SpecialOffersRvAdapter mSpecialOffersRvAdapter;
    private SpecialOffersFragmentViewModel mViewModel;
    private boolean mDidScroll;


    public static SpecialOffersFragment newInstance(OfferType offerType) {

        Bundle args = new Bundle();
        args.putSerializable(ARGS_OFFER_TYPE, offerType);

        SpecialOffersFragment fragment = new SpecialOffersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SpecialOffersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_special_offers, container, false);
        ButterKnife.bind(this, v);
        setOfferType();
        initRecipeRv();
        subscribeObserver();
        return v;
    }

    private void setOfferType() {
        switch (mOfferType) {
            case SPECIAL_OFFER:
                mTxtToolbarTitle.setText(getString(R.string.navbarMenuSpecialOffer));
                break;
            case MOST_SOLD:
                mTxtToolbarTitle.setText(getString(R.string.navbarMenuMostSoled));
                break;
            case MOST_SEEN:
                mTxtToolbarTitle.setText(getString(R.string.navbarMenuMostSeen));
                break;
            case NEWEST:
                mTxtToolbarTitle.setText(getString(R.string.navbarMenuNewest));
                break;
        }
        mViewModel.setSpecialOffersProductList(mOfferType);
    }

    private void initVariables() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(SpecialOffersFragmentViewModel.class);
        mOfferType = (OfferType) getArguments().getSerializable(ARGS_OFFER_TYPE);
    }

    private void initRecipeRv() {
        mPrgLoading.start();
        mSpecialOffersRvAdapter = new SpecialOffersRvAdapter();

        // These lines of code below are to solve the problem of recyclerview items when the parent layout is
        // CardView and no margins are applied around the items
        /*LayoutMarginDecoration marginDecoration = new LayoutMarginDecoration(2, 4);
        marginDecoration.setPadding(mSpecialOffersRv, 2, 2, 4, 4);*/
        //mSpecialOffersRv.addItemDecoration(marginDecoration);
        GridLayoutManager gridLayoutManager = mRvHelper.getGridLayoutManager(getContext(), RecyclerViewHelper.Orientation.VERTICAL, 2);
        RecyclerView recipeRV = mRvHelper.buildRecyclerView(gridLayoutManager, mSpecialOffersRv, mSpecialOffersRvAdapter);
        recipeRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!mDidScroll && !recipeRV.canScrollVertically(1)) {
                    mViewModel.searchNextPage();
                    // this field prevents calling method for more than once
                    // when user has reached the end of the recyclerview and
                    // tries scrolling down
                    mDidScroll = true;
                }
            }
        });
    }

    protected void subscribeObserver() {
        mViewModel.getSpecialRecipesMLD().observe(this, listResource -> {
            //onChange()
            if (listResource != null) {
                Log.d(LOG_TAG, "onChanged: status: " + listResource.status);
                if (listResource.data != null)
                    Testing.printRecipes(listResource.data, LOG_TAG);
                mPrgLoading.stop();
            }
            mDidScroll = false;
        });


        mViewModel.isQueryExhaustedLd().observe(this, isExhausted -> {
            Log.d(LOG_TAG, "subscribeObserver: isExhausted = " + isExhausted);
            mSpecialOffersRvAdapter.setQueryExhausted(isExhausted);
        });
    }

    @OnClick(R.id.imgToolbarBackArrow)
    void setBackClickListener() {
        getActivity().finish();
        resetRecipeList();
    }

    @OnClick(R.id.imgToolbarShoppingBasket)
    void setShoppingBasketClickListener() {
        Intent intent = ShoppingBasketActivity.newIntent(getContext());
        startActivity(intent);
    }

    private void resetRecipeList() {
        new Handler().postDelayed(() -> {
            mViewModel.getSpecialRecipesMLD().postValue(null);
            mSpecialOffersRvAdapter = null;
        }, 1000);
    }


}
