package com.developer.smmousavi.foodino.ui.activities.prodcutdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.activities.drawer.BaseDrawerActivity;
import com.developer.smmousavi.foodino.ui.fragments.productdetail.ProductDetailFragment;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

public class ProductDetailDrawerActivity extends BaseDrawerActivity {

    public static final String EXTRA_PRODUCT_ID = "extraProductId";

    public static Intent newIntent(Context context, String productId) {
        Intent intent = new Intent(context, ProductDetailDrawerActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getFragmentId() {
        return R.id.flDrawerContentFragmentContainer;
    }

    @Override
    public Fragment getFragmentObject() {
        String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);
        return ProductDetailFragment.newInstance(productId);
    }

    @Override
    public String getFragmentTag() {
        return ProductDetailFragment.TAG;
    }

    @Override
    public boolean isToolbarVisible() {
        return false;
    }
}