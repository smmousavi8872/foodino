package com.developer.smmousavi.foodino.activities.settings;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.settings.SettingsFragment;

import androidx.fragment.app.Fragment;

public class SettingsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, SettingsActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return SettingsFragment.newInstance();
    }

    @Override
    public String getTag() {
        return SettingsFragment.TAG;
    }
}
