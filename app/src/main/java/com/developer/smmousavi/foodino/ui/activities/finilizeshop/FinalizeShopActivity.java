package com.developer.smmousavi.foodino.ui.activities.finilizeshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.developer.smmousavi.foodino.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.ui.fragments.finalizeshop.FinalizeShopFragment;

public class FinalizeShopActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, FinalizeShopActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public BaseDaggerFragment createFragment() {
        return FinalizeShopFragment.newInstance();
    }

    @Override
    public String getTag() {
        return FinalizeShopFragment.TAG;
    }
}
