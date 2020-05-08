package com.developer.smmousavi.foodino.ui.viewholders.specialoffers;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.activities.prodcutdetail.ProductDetailDrawerActivity;
import com.developer.smmousavi.foodino.base.BaseViewHolder;
import com.developer.smmousavi.foodino.ui.fragments.specialoffers.OnRecipeClickListener;
import com.developer.smmousavi.foodino.helper.GlideHelper;
import com.developer.smmousavi.foodino.models.Recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class SpecialOffersVH<T extends Recipe> extends BaseViewHolder<T> implements View.OnLongClickListener {

    private static final String TAG = "SpecialOffersVH";

    private OnRecipeClickListener mRecipeClickListener;

    @BindView(R.id.imgProductPreviewImage)
    AppCompatImageView mImgProductImage;
    @BindView(R.id.txtProductPreviewTitle)
    AppCompatTextView mTxtProductTittle;
    @BindView(R.id.txtProductMainPrice)
    AppCompatTextView mTxtProcdutMainPrice;
    @BindView(R.id.txtProductDiscountPrice)
    AppCompatTextView mTxtProductDicountPrice;

    private View mItemView;
    private Recipe mItem;

    public SpecialOffersVH(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
    }

    @Override
    public void onBind(Recipe item) {
        mItem = item;
        GlideHelper.build(R.drawable.ic_fo_menu, mImgProductImage.getContext(), item.getImageUrl(), mImgProductImage);
        mTxtProductTittle.setText(item.getTitle());
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: is called.");
        String recipeId = mItem.getRecipeId();
        Intent intent = ProductDetailDrawerActivity.newIntent(v.getContext(), recipeId);
        v.getContext().startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d(TAG, "onLongClick: is called.");
        return false;
    }
}
