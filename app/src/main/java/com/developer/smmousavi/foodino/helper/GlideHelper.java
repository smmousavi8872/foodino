package com.developer.smmousavi.foodino.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.DrawableRes;

public class GlideHelper {
    public static void build(@DrawableRes int placeHolder, Context context, String url, ImageView owner) {
        RequestOptions mRequestOptions = new RequestOptions().placeholder(placeHolder);
        Glide.with(context)
            .setDefaultRequestOptions(mRequestOptions)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(owner);
    }
}
