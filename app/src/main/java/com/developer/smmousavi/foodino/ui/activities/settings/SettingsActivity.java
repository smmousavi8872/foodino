package com.developer.smmousavi.foodino.ui.activities.settings;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.ui.fragments.settings.SettingsFragment;

public class SettingsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, SettingsActivity.class);
        return intent;
    }

    @Override
    public BaseDaggerFragment createFragment() {
        return SettingsFragment.newInstance();
    }

    @Override
    public String getTag() {
        return SettingsFragment.TAG;
    }
}
