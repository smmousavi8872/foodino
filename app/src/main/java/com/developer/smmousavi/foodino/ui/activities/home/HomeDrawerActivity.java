package com.developer.smmousavi.foodino.ui.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.activities.drawer.BaseDrawerActivity;
import com.developer.smmousavi.foodino.ui.fragments.home.HomeDrawerFragment;

import androidx.fragment.app.Fragment;

public class HomeDrawerActivity extends BaseDrawerActivity {

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, HomeDrawerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getFragmentId() {
        return R.id.flDrawerContentFragmentContainer;
    }

    @Override
    public Fragment getFragmentObject() {
        return HomeDrawerFragment.newInstance();
    }

    @Override
    public String getFragmentTag() {
        return HomeDrawerFragment.TAG;
    }

    @Override
    public boolean isToolbarVisible() {
        return true;
    }
}
