package com.developer.smmousavi.foodino.ui.fragments.categorylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.adapters.categorylist.CategoryListTabRvAdapter;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;
import com.developer.smmousavi.foodino.models.Category;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListTabLayoutFragment extends BaseDaggerFragment {

    public static final String TAG = "com.developer.smmousavi.digishop.fragment.categorylist.CategoryListTabFragment.TAG";

    public static final String ARGS_TAB_CATEGORY = "argsTabCategory";

    @BindView(R.id.rvCategoryListTabContainer)
    RecyclerView mCategoryListRv;

    private CategoryListTabRvAdapter mCategroyListAdapter;
    private Category mTabCategory;

    @Inject
    RecyclerViewHelper mRvHelper;

    public static CategoryListTabLayoutFragment newInstance(Category tabCategory) {

        Bundle args = new Bundle();

        args.putSerializable(ARGS_TAB_CATEGORY, tabCategory);
        CategoryListTabLayoutFragment fragment = new CategoryListTabLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryListTabLayoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category_list_tab, container, false);

        ButterKnife.bind(this, v);

        initArgParameters();

        setCategoryListRv();

        return v;
    }

    private void initArgParameters() {
        mTabCategory = (Category) getArguments().getSerializable(ARGS_TAB_CATEGORY);
    }

    protected void setCategoryListRv() {
        // this rotation is because of view pager rotation(180), to fix rtl direction issue.
        mCategoryListRv.setRotationY(180);
        if (mCategroyListAdapter == null) {
            mCategroyListAdapter = new CategoryListTabRvAdapter(mTabCategory.abstractSubcategoryList());
        } else {
            mCategroyListAdapter.setItemList(mTabCategory.abstractSubcategoryList());
            mCategroyListAdapter.notifyDataSetChanged();
        }
        LinearLayoutManager layoutManager = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.VERTICAL, false);
        mRvHelper.buildRecyclerView(layoutManager, mCategoryListRv, mCategroyListAdapter);
    }

}
