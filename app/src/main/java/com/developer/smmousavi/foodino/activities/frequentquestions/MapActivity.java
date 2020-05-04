package com.developer.smmousavi.foodino.activities.frequentquestions;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.map.MapFragment;

import androidx.fragment.app.Fragment;

public class MapActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, MapActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return MapFragment.newInstance(null);
    }

    @Override
    public String getTag() {
        return MapFragment.TAG;
    }
}
