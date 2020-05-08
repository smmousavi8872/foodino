package com.developer.smmousavi.foodino.ui.viewholders.categorypreview;

import android.content.Intent;
import android.view.View;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.activities.categorylist.CategoryListActivity;
import com.developer.smmousavi.foodino.base.BaseViewHolder;
import com.developer.smmousavi.foodino.models.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class CategoryPreviewVH<T extends Category> extends BaseViewHolder<T> {

    @BindView(R.id.txtRowCategoryPreviewTitle)
    AppCompatTextView mCategoryPreviewTitle;

    private View mItemView;

    public CategoryPreviewVH(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
    }

    @Override
    public void onBind(Category category) {
        Category item = category;
        mCategoryPreviewTitle.setText(item.title());
    }

    @Override
    public void onClick(View v) {
        Intent intent = CategoryListActivity.newIntent(v.getContext(), getAdapterPosition());
        v.getContext().startActivity(intent);
    }
}
