package com.developer.smmousavi.foodino.ui.activities.categorylist;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.ui.fragments.categorylist.CategoryListFragment;

public class CategoryListActivity extends SingleFragmentActivity {

    public static final String EXTRA_SELECTED_PAGE_TAB = "extraSelectedPageTab";

    public static Intent newIntent(Context orgin, int selectedPageTab) {
        Intent intent = new Intent(orgin, CategoryListActivity.class);
        intent.putExtra(EXTRA_SELECTED_PAGE_TAB, selectedPageTab);
        return intent;
    }

    @Override
    public BaseDaggerFragment createFragment() {
        int selectedPage = getIntent().getIntExtra(EXTRA_SELECTED_PAGE_TAB, 0);
        return CategoryListFragment.newInstance(selectedPage);
    }

    @Override
    public String getTag() {
        return CategoryListFragment.TAG;
    }
}
