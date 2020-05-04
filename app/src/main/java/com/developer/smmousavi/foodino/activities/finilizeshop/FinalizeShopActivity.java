package com.developer.smmousavi.foodino.activities.finilizeshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.finalizeshop.FinalizeShopFragment;

import androidx.fragment.app.Fragment;

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
    public Fragment createFragment() {
        return FinalizeShopFragment.newInstance();
    }

    @Override
    public String getTag() {
        return FinalizeShopFragment.TAG;
    }
}
