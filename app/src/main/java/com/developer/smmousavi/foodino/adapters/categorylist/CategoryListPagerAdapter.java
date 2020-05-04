package com.developer.smmousavi.foodino.adapters.categorylist;

import java.util.LinkedHashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryListPagerAdapter extends FragmentPagerAdapter {

    LinkedHashMap<String, Fragment> mFragmentMap;

    public CategoryListPagerAdapter(FragmentManager fm, LinkedHashMap<String, Fragment> fragmentMap) {
        super(fm);
        mFragmentMap = fragmentMap;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return getFragmentByPosition(position);
    }

    @Override
    public int getCount() {
        return mFragmentMap.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getTitleByPosition(position);
    }

    public Fragment getFragmentByPosition(int position) {
        int i = 0;
        for (Fragment f : mFragmentMap.values()) {
            if (i == position)
                return f;
            i++;
        }
        return null;
    }

    public String getTitleByPosition(int position) {
        int i = 0;
        for (String s : mFragmentMap.keySet()) {
            if (i == position)
                return s;
            i++;
        }
        return null;
    }
}
