package com.developer.smmousavi.foodino.ui.activities.aboutus;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.ui.fragments.aboutus.AboutUsFragment;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;

public class AboutUsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context origin) {
        Intent intent = new Intent(origin, AboutUsActivity.class);
        return intent;
    }

    @Override
    public BaseDaggerFragment createFragment() {
        return AboutUsFragment.newInstance();
    }

    @Override
    public String getTag() {
        return AboutUsFragment.TAG;
    }
}
