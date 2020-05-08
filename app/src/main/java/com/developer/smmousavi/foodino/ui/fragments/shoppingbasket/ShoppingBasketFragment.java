package com.developer.smmousavi.foodino.ui.fragments.shoppingbasket;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.adapters.shoppingbasket.ShoppingBasketRvAdapter;
import com.developer.smmousavi.foodino.adapters.shoppingbasket.ShoppingBasketSuggestionsRvAdapter;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.ui.activities.finilizeshop.FinalizeShopActivity;
import com.developer.smmousavi.foodino.ui.fragments.alertdialog.AlertDialogFragment;
import com.developer.smmousavi.foodino.ui.fragments.alertdialog.OnDialogButtonClickListener;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.callback.EmptyBasketCallback;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.callback.UpdateBasketContentCallback;
import com.developer.smmousavi.foodino.util.Animations;
import com.developer.smmousavi.foodino.util.PriceUtils;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingBasketFragment extends BaseDaggerFragment implements
    EmptyBasketCallback, UpdateBasketContentCallback {

    public static final String TAG = "ShoppingBasketFragment";
    public static final String ALERT_DIALOG_FM_TAG = "alertDialogFmTag";
    private static final String LOG_TAG = "LOG_TAG";

    @BindView(R.id.imgToolbarCloseShoppingBasket)
    AppCompatImageView mImgClose;
    @BindView(R.id.imgToolbarEmptyShoppingBasket)
    AppCompatImageView mImgEmptyShoppingBasket;
    @BindView(R.id.rvShoppingBasket)
    RecyclerView mShoppingBasketRv;
    @BindView(R.id.rvShoppingBasketSuggestions)
    RecyclerView mProductsSuggestionsRv;
    @BindView(R.id.prgShoppingBasketLoading)
    RotateLoading mPrgLoading;
    @BindView(R.id.txtTotalShopPrice)
    AppCompatTextView mTxtTotalShopPrice;
    @BindView(R.id.txtFinalizeShop)
    AppCompatTextView mTxtFinalizeShop;
    @BindView(R.id.txtBasketIsEmpty)
    AppCompatTextView mTxtBasketIsEmpty;
    @BindView(R.id.cvFinalizeShop)
    CardView mCvFinalizeShopContainer;
    @BindView(R.id.cvTotalShopPrice)
    CardView mCvTotalShopPriceContainer;
    @BindView(R.id.txtShoppingBasketSuggestionsTitle)
    AppCompatTextView mTxtShoppingBasketSuggestionsTitle;
    @BindView(R.id.svShopListContainer)
    NestedScrollView mSvShoppingListContainer;

    @Inject
    ShoppingBasketRvAdapter mShoppingBasketRvAdapter;
    @Inject
    ShoppingBasketSuggestionsRvAdapter mProductSuggestionsRvAdapter;
    @Inject
    List<Product> mShopList;
    @Inject
    RecyclerViewHelper mRvHelper;
    @Inject
    ViewModelProviderFactory mProviderFactory;

    private double mTotalPrice;
    private ShoppingBasketFragmentViewModel mViewModel;


    public static ShoppingBasketFragment newInstance() {

        Bundle args = new Bundle();

        ShoppingBasketFragment fragment = new ShoppingBasketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ShoppingBasketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shopping_basket, container, false);

        ButterKnife.bind(this, v);

        initVariables();

        new Handler().postDelayed(() -> {
            setShopListRv();
            subscribeObservers();
        }, 1000);

        new Handler().postDelayed(() -> {
            setTotalShopPrice();
            showBasketAccessories(true);
            Animations.setAnimation(Animations.FADE_IN, mSvShoppingListContainer);
            mTotalPrice = getTotalPrice();
            mPrgLoading.stop();
        }, 1200);

        return v;
    }

    private void initVariables() {
        mPrgLoading.start();
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(ShoppingBasketFragmentViewModel.class);
    }

    public void subscribeObservers() {
        mViewModel.getSuggestedRecipes();
        mViewModel.getRecipesMLD().observe(this, listResource -> {
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        Log.d(LOG_TAG, "subscribeObserver: cache has been refreshed.");
                        Log.d(LOG_TAG, "subscribeObserver: status: SUCCESS, #recipes: " + listResource.data.size());
                        setProductsSuggestionsRv(listResource.data);
                        mPrgLoading.stop();
                        break;
                    case ERROR:
                        Log.e(LOG_TAG, "subscribeObserver: can not refresh the cache.");
                        Log.e(LOG_TAG, "subscribeObserver: Error message: " + listResource.message);
                        Log.e(LOG_TAG, "subscribeObserver: status: ERROR, #recipes: " + listResource.data.size());
                        setProductsSuggestionsRv(listResource.data);
                        mPrgLoading.stop();
                        break;
                }
            }

        });
    }

    protected void setShopListRv() {
        if (mShoppingBasketRvAdapter != null) {
            mShoppingBasketRvAdapter.setItemList(mShopList);
            mShoppingBasketRvAdapter.notifyDataSetChanged();

            initShopListRvAdapter();
        }
        LinearLayoutManager layoutManager = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.VERTICAL, false);
        mRvHelper.buildRecyclerView(layoutManager, mShoppingBasketRv, mShoppingBasketRvAdapter);
    }

    protected void setProductsSuggestionsRv(List<Recipe> recipes) {
        mProductSuggestionsRvAdapter.setItemList(recipes);
        /*
            These lines of code below are to solve the problem of recyclerview items when the parent layout is
            CardView and no margins are applied around the items
        */
        LayoutMarginDecoration marginDecoration = new LayoutMarginDecoration(1, 24);
        marginDecoration.setPadding(mProductsSuggestionsRv, 4, 4, 4, 4);
        mProductsSuggestionsRv.addItemDecoration(marginDecoration);
        LinearLayoutManager layoutManager = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.HORIZONTAL, true);
        mRvHelper.buildRecyclerView(layoutManager, mProductsSuggestionsRv, mProductSuggestionsRvAdapter);
    }

    protected void initShopListRvAdapter() {
        mShoppingBasketRvAdapter.setEmptyListCallback(this);
        mShoppingBasketRvAdapter.setUpdateListCallback(this);
        mShoppingBasketRvAdapter.setFm(getFragmentManager());
    }

    private void showBasketAccessories(boolean show) {
        if (!show) {
            mTxtShoppingBasketSuggestionsTitle.setVisibility(View.GONE);
            mTxtBasketIsEmpty.setVisibility(View.VISIBLE);
            mImgEmptyShoppingBasket.setEnabled(false);
            Animations.setAnimation(Animations.SLIDE_UP, mCvTotalShopPriceContainer);
            Animations.setAnimation(Animations.SLIDE_OUT_DOWN, mCvFinalizeShopContainer);
            new Handler().postDelayed(() -> mCvFinalizeShopContainer.setVisibility(View.INVISIBLE), 500);
        } else {
            mTxtShoppingBasketSuggestionsTitle.setVisibility(View.VISIBLE);
            mTxtBasketIsEmpty.setVisibility(View.GONE);
            mImgEmptyShoppingBasket.setEnabled(true);
            Animations.setAnimation(Animations.FADE_IN_FAST, mCvTotalShopPriceContainer);
            Animations.setAnimation(Animations.FADE_IN_FAST, mCvFinalizeShopContainer);
            new Handler().postDelayed(() -> mCvFinalizeShopContainer.setVisibility(View.VISIBLE), 500);
        }
    }

    private boolean isBasketEmpty() {
        return mShopList.size() == 0;
    }

    private void setTotalShopPrice() {
        double totalPrice = getTotalPrice();
        mTxtTotalShopPrice.setText(PriceUtils.getFormattedPrice(totalPrice));
    }


    private void updateTotalShopPrice(double priceDifference) {
        mTotalPrice = mTotalPrice + priceDifference;
        mTxtTotalShopPrice.setText(PriceUtils.getFormattedPrice(mTotalPrice));
    }

    private double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : mShopList)
            if (product.discountPercent() != null)
                totalPrice += (product.price() - product.price() * product.discountPercent() / 100);
            else
                totalPrice += product.price();

        return totalPrice;
    }


    @Override
    public void onEmptyFinished() {
        mPrgLoading.stop();
        showBasketAccessories(false);
    }

    @Override
    public void onProductCountChanged(double priceDifference) {
        if (priceDifference > 0.1 || priceDifference < -0.1)
            updateTotalShopPrice(priceDifference);
    }

    @Override
    public void onProductRemoved(int position, double priceDifference) {
        mShoppingBasketRvAdapter.removeItem(position);
        Timber.d("onProductRemoved: price difference %s", priceDifference);
        updateTotalShopPrice(priceDifference);
        if (isBasketEmpty()) {
            showBasketAccessories(false);
        }
    }

    @OnClick(R.id.imgToolbarCloseShoppingBasket)
    void closeShoppingBasketListener() {
        getActivity().finish();
    }

    @OnClick(R.id.imgToolbarEmptyShoppingBasket)
    void setImgEmptyShoppingBasketListener() {
        String title = getString(R.string.emptyBasketAlertTitle);
        String message = getString(R.string.emptyBasketAlertMessage);
        String positiveButtonText = getString(R.string.alertDialogPositiveButton);
        String negativeButtonText = getString(R.string.alertDialogNegetiveButtonText);
        AlertDialogFragment dialog = AlertDialogFragment.newInstance(title, message, positiveButtonText, negativeButtonText);
        dialog.setCancelable(false);
        dialog.setButtonClickListener(new OnDialogButtonClickListener() {
            @Override
            public void onPositiveButtonClick(View v) {
                mPrgLoading.start();
                mShoppingBasketRvAdapter.emptyItemList();
                mProductSuggestionsRvAdapter.emptyItemList();
                dialog.dismiss();
            }

            @Override
            public void onNegativeButtonClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show(getFragmentManager(), ALERT_DIALOG_FM_TAG);
    }

    @OnClick(R.id.cvFinalizeShop)
    public void setFinalizeShopListener() {
        Intent intent = FinalizeShopActivity.newIntent(getActivity());
        startActivity(intent);
    }

}