package com.developer.smmousavi.foodino.fragments.finalizeshop;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinalizeShopFragment extends BaseDaggerFragment {

    private FinalizeShopFragmentViewModel mViewModel;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    public static final String TAG = "FinalizeShopFragmentTag";


    public FinalizeShopFragment() {
        // Required empty public constructor
    }

    public static FinalizeShopFragment newInstance() {

        Bundle args = new Bundle();

        FinalizeShopFragment fragment = new FinalizeShopFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_finalize_shop, container, false);
        ButterKnife.bind(this, v);

        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(FinalizeShopFragmentViewModel.class);

        return v;
    }



}
