package com.developer.smmousavi.foodino.ui.viewholders.productpreview;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.base.BaseViewHolder;
import com.developer.smmousavi.foodino.helper.GlideHelper;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.ui.activities.prodcutdetail.ProductDetailDrawerActivity;
import com.developer.smmousavi.foodino.util.PriceUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class ProductPreviewVH<T extends Recipe> extends BaseViewHolder<T> implements View.OnLongClickListener {

    @BindView(R.id.imgProductPreviewImage)
    AppCompatImageView mImgProductPreviewImage;
    @BindView(R.id.txtProductPreviewTitle)
    AppCompatTextView mTxtProductPreviewTitle;
    @BindView(R.id.txtProductMainPrice)
    AppCompatTextView mTxtProductPreviewMainPrice;
    @BindView(R.id.txtProductDiscountPrice)
    AppCompatTextView mTxtProductPreviewPriceWithDiscount;
    @BindView(R.id.txtProductFinished)
    AppCompatTextView mTxtProductFinished;
    @BindView(R.id.txtTomanRed)
    AppCompatTextView mTxtRedToman;
    @BindView(R.id.txtTomanGreen)
    AppCompatTextView mTxtGreenToman;

    private View mItemView;
    private Recipe mProduct;

    public ProductPreviewVH(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        mItemView.setOnLongClickListener(this);
    }

    @Override
    public void onBind(Recipe recipe) {
        mProduct = recipe;
        GlideHelper.build(R.drawable.foodino_place_holder,
            mItemView.getContext(),
            recipe.getImageUrl(),
            mImgProductPreviewImage);

        mTxtProductPreviewTitle.setText(recipe.getTitle());
//        if (recipe.isAvailable()) {
        double priceWithDiscount = PriceUtils.getPriceWithDiscount(160000, 5);
        mTxtProductPreviewMainPrice.setText(PriceUtils.getFormattedPrice(160000));
        mTxtProductPreviewPriceWithDiscount.setText(PriceUtils.getFormattedPrice(priceWithDiscount));
        mTxtProductFinished.setVisibility(View.INVISIBLE);
        mTxtProductPreviewPriceWithDiscount.setVisibility(View.VISIBLE);
        mTxtProductPreviewMainPrice.setVisibility(View.VISIBLE);
        mTxtRedToman.setVisibility(View.VISIBLE);
        mTxtGreenToman.setVisibility(View.VISIBLE);
        /*} else {
            mTxtProductFinished.setVisibility(View.VISIBLE);
            mTxtProductPreviewPriceWithDiscount.setVisibility(View.INVISIBLE);
            mTxtProductPreviewMainPrice.setVisibility(View.INVISIBLE);
            mTxtRedToman.setVisibility(View.INVISIBLE);
            mTxtGreenToman.setVisibility(View.INVISIBLE);
        }*/
    }

    @Override
    public void onClick(View v) {
        String productId = mProduct.getRecipeId();
        Intent intent = ProductDetailDrawerActivity.newIntent(v.getContext(), productId);
        v.getContext().startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(v.getContext(), "Show User Product Panel", Toast.LENGTH_SHORT).show();
        //TODO: Should show a small panel above the item which enables the user
        // to add the product to shopping basket, favariot prodcuts list and so forth

        return true;
    }
}
