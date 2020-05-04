package com.developer.smmousavi.foodino.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.application.BaseApplication;

public class ImageUtils {

    public static void loadImage( String url, ImageView imageView) {
        Glide.with(BaseApplication.getAppContext())
            .load(url)
            .placeholder(R.drawable.ic_dk)
            .centerCrop()
            .into(imageView);
    }
}
