package com.developer.smmousavi.foodino.activities.shoppingbasket;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.shoppingbasket.ShoppingBasketFragment;

import androidx.fragment.app.Fragment;

public class ShoppingBasketActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, ShoppingBasketActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ShoppingBasketFragment.newInstance();
    }

    @Override
    public String getTag() {
        return ShoppingBasketFragment.TAG;
    }
}