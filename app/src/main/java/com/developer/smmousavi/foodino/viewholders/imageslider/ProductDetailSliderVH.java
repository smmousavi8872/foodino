package com.developer.smmousavi.foodino.viewholders.imageslider;

import android.view.View;
import android.widget.ImageView;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.helper.GlideHelper;
import com.smarteist.autoimageslider.SliderViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailSliderVH extends SliderViewAdapter.ViewHolder {

    private View mItemView;

    @BindView(R.id.imgImageSlideBackground)
    ImageView mImgSlideBackground;

    public ProductDetailSliderVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mItemView = itemView;
    }

    public void bind(String imageUrl) {
        loadImage(imageUrl);
    }

    private void loadImage(String url) {
        GlideHelper.build(R.drawable.ic_fo_menu, mItemView.getContext(), url, mImgSlideBackground);
    }
}
