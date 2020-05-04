package com.developer.smmousavi.foodino.adapters.productdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.viewholders.imageslider.ProductDetailSliderVH;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class ProductDetailSliderAdapter extends SliderViewAdapter<ProductDetailSliderVH> {

    private List<String> mImageUrlList;

    public ProductDetailSliderAdapter(List<String> imageUrls) {
        mImageUrlList = imageUrls;
    }

    @Override
    public ProductDetailSliderVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_slider, null);
        return new ProductDetailSliderVH(view);
    }

    @Override
    public void onBindViewHolder(ProductDetailSliderVH viewHolder, int position) {
        viewHolder.bind(mImageUrlList.get(position));

    }

    @Override
    public int getCount() {
        return mImageUrlList.size();
    }
}
