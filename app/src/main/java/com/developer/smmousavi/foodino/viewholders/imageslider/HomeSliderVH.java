package com.developer.smmousavi.foodino.viewholders.imageslider;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.models.Banner;
import com.smarteist.autoimageslider.SliderViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSliderVH extends SliderViewAdapter.ViewHolder {

    private View mItemView;

    @BindView(R.id.imgImageSlideBackground)
    ImageView mImgSlideBackground;

    public HomeSliderVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mItemView = itemView;
    }

    public void bind(Banner bannerItem) {
        loadImage(bannerItem.url());
    }

    private void loadImage(String url) {
        Glide.with(mItemView)
            .load(url)
            .placeholder(R.drawable.ic_dk)
            .centerCrop()
            .into(mImgSlideBackground);
    }

}
