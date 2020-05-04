package com.developer.smmousavi.foodino.activities.aboutus;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.aboutus.AboutUsFragment;

import androidx.fragment.app.Fragment;

public class AboutUsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context origin) {
        Intent intent = new Intent(origin, AboutUsActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return AboutUsFragment.newInstance();
    }

    @Override
    public String getTag() {
        return AboutUsFragment.TAG;
    }
}
