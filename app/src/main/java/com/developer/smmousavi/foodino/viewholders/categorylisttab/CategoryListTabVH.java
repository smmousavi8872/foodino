package com.developer.smmousavi.foodino.viewholders.categorylisttab;

import android.view.View;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.base.BaseViewHolder;
import com.developer.smmousavi.foodino.helper.GlideHelper;
import com.developer.smmousavi.foodino.models.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListTabVH<T extends Category> extends BaseViewHolder<T> {

    @BindView(R.id.txtCategoryTitle)
    AppCompatTextView mTxtCategoryTitle;
    @BindView(R.id.imgCategoryImage)
    AppCompatImageView mImgCategoryImage;

    private View mItemView;

    public CategoryListTabVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mItemView = itemView;

    }

    @Override
    public void bind(Category category) {
        mTxtCategoryTitle.setText(category.title());
        GlideHelper.build(R.drawable.ic_fo_menu, mItemView.getContext(),
            category.iconUrl(), mImgCategoryImage);
    }

    @Override
    public void onClick(View v) {

    }
}