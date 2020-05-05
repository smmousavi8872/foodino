package com.developer.smmousavi.foodino.activities.map;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.fragments.map.MapFragment;

public class MapActivity extends SingleFragmentActivity {

    private BaseDaggerFragment mGuestFragment;

    public static Intent newIntent(Context origin) {
        Intent intent = new Intent(origin, MapActivity.class);
        return intent;
    }

    @Override
    public BaseDaggerFragment createFragment() {
        mGuestFragment = MapFragment.newInstance(null);
        return mGuestFragment;
    }

    @Override
    public String getTag() {
        return MapFragment.TAG;
    }

}
