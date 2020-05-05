package com.developer.smmousavi.foodino.fragments.base;


import android.os.Bundle;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseDaggerFragment extends DaggerFragment {

    public static final String TAG = "BaseDaggerFragment";

    private FragmentManager mFm;

    public BaseDaggerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getFragmentManager();
    }

    public void replaceFragment(@IdRes int containerId,
                                @NonNull Fragment newFragment,
                                @NonNull String newTag,
                                @AnimatorRes @AnimRes int enterAnimId,
                                @AnimatorRes @AnimRes int exitAnimId,
                                boolean popPrevious) {

        Fragment foundFragment = mFm.findFragmentByTag(newTag);
        if (popPrevious) {
            int frgCount = mFm.getBackStackEntryCount();
            if (frgCount > 0)
                mFm.popBackStack();
        }

        if (foundFragment == null)
            mFm.beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(containerId, newFragment, newTag)
                .addToBackStack(newTag)
                .commit();
        else
            mFm.beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(containerId, newFragment, newTag)
                .commit();
    }

    public void removeFragment(@NonNull Fragment fragment) {
        mFm.beginTransaction()
            .remove(fragment)
            .commit();
    }

    public Fragment findFragmentByTag(String fragmentTag) {
        return mFm.findFragmentByTag(fragmentTag);
    }

}
