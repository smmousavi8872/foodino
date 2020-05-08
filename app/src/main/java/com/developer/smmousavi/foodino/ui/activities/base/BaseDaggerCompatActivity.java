package com.developer.smmousavi.foodino.ui.activities.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.developer.smmousavi.foodino.R;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseDaggerCompatActivity extends DaggerAppCompatActivity {

    public FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getSupportFragmentManager();
    }

    public ProgressBar mProgressBar;

    @Override
    public void setContentView(int layoutResID) {
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base_dagger_app_compat, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.acitivty_content_wrapper);
        mProgressBar = constraintLayout.findViewById(R.id.prgFragmentLoading);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(constraintLayout);
    }

    public void showProgressBar(boolean visibility) {
        mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}
