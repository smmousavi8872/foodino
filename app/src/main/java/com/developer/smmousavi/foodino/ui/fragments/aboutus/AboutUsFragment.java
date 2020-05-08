package com.developer.smmousavi.foodino.ui.fragments.aboutus;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends BaseDaggerFragment {

    @SuppressWarnings("SpellCheckingInspection")
    public static final String TAG = "com.developer.smmousavi.digishop.fragment.aboutus.AboutUsFragment";

    public static AboutUsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AboutUsFragment fragment = new AboutUsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about_us, container, false);

        return v;
    }

}
