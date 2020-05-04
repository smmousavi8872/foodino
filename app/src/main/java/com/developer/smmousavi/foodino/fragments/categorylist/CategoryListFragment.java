package com.developer.smmousavi.foodino.fragments.categorylist;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.activities.categorylist.CategoryListActivity;
import com.developer.smmousavi.foodino.adapters.categorylist.CategoryListPagerAdapter;
import com.developer.smmousavi.foodino.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.models.Category;
import com.google.android.material.tabs.TabLayout;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends BaseDaggerFragment {

    public static final String TAG = "com.developer.smmousavi.digishop.fragment.categorylist.CategoryListFragment";

    public static final String ARGS_SELECTED_PAGE_TAB = "argsSelectedPageTab";


    @BindView(R.id.tbMainToolbar)
    Toolbar mToolbar;
    @BindView(R.id.imgToolbarBackArrow)
    AppCompatImageView mBackArrow;
    @BindView(R.id.tabLayoutCategoryList)
    TabLayout mTabLayout;
    @BindView(R.id.pagerCategoryList)
    ViewPager mViewPager;

    @Inject
    List<Category> mCategoryList;
    @Inject
    LinkedHashMap<String, Fragment> mFragmentMap;
    @Inject
    ViewModelProviderFactory mProviderFactory;

    private int mSelectedPageTab;
    private PagerAdapter mPagerAdapter;
    private CategoryListFragmentViewModel mViewModel;


    public static CategoryListFragment newInstance(int selectedPageTab) {

        Bundle args = new Bundle();

        args.putInt(ARGS_SELECTED_PAGE_TAB, selectedPageTab);

        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category_list, container, false);

        ButterKnife.bind(this, v);
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(CategoryListFragmentViewModel.class);

        initToolbar();

        initArgumentsParameter();

        initTabLayout();

        return v;
    }

    private void initArgumentsParameter() {
        mSelectedPageTab = getArguments().getInt(ARGS_SELECTED_PAGE_TAB);
    }

    private void initToolbar() {
        ((CategoryListActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    private void initTabLayout() {
        mPagerAdapter = new CategoryListPagerAdapter(getFragmentManager(), mFragmentMap);
        mViewPager.setAdapter(mPagerAdapter);
        // this rotation with that of recyclerview(in CategoryListTabFragment) results in rtl swipe for viewpager.
        mViewPager.setRotationY(180);
        mTabLayout.setupWithViewPager(mViewPager);

        setViewPagerCurrentPage(mSelectedPageTab);
    }

    void setViewPagerCurrentPage(int pageIndex) {
        mViewPager.setCurrentItem(pageIndex);
        mTabLayout.setScrollPosition(pageIndex, 0f, true);
    }

    @OnClick(R.id.imgToolbarBackArrow)
    void setBackArrowListener() {
        getActivity().finish();
    }

}
