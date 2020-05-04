package com.developer.smmousavi.foodino.viewholders;

import android.view.View;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.base.BaseViewHolder;
import com.developer.smmousavi.foodino.models.Recipe;
import com.victor.loading.rotate.RotateLoading;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadMoreVH<T extends Recipe> extends BaseViewHolder<T> {

    @BindView(R.id.prgLoadMore)
    RotateLoading mLoading;

    public LoadMoreVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(T item) {
        mLoading.start();
    }

    @Override
    public void onClick(View v) {

    }
}
