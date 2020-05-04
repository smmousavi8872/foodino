package com.developer.smmousavi.foodino.adapters.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.models.Banner;
import com.developer.smmousavi.foodino.viewholders.imageslider.HomeSliderVH;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class HomeSliderAdapter extends SliderViewAdapter<HomeSliderVH> {

    private List<Banner> mBanners;

    public HomeSliderAdapter(List<Banner> banners) {
        mBanners = banners;
    }

    public void setItemList(List<Banner> banners) {
        mBanners = banners;
    }

    @Override
    public HomeSliderVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_slider, null);
        return new HomeSliderVH(inflate);
    }

    @Override
    public void onBindViewHolder(HomeSliderVH viewHolder, int position) {
        viewHolder.bind(mBanners.get(position));
    }

    @Override
    public int getCount() {
        return mBanners.size();
    }

}
