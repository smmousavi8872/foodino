package com.developer.smmousavi.foodino.ui.viewholders.imageslider;

import android.view.View;
import android.widget.ImageView;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.helper.GlideHelper;
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
        GlideHelper.build(R.drawable.foodino_place_holder, mItemView.getContext(), bannerItem.url(), mImgSlideBackground);
    }
}
