package com.developer.smmousavi.foodino.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.developer.smmousavi.foodino.R;

import androidx.annotation.AnimRes;


public class Animations {

    public static final int SLIDE_UP = R.anim.slide_up;
    public static final int SLIDE_DOWN = R.anim.slide_down;
    public static final int SLIDE_IN_UP = R.anim.slide_in_top;
    public static final int SLIDE_IN_TOP_FAST = R.anim.slide_in_top_fast;
    public static final int SLIDE_IN_BOTTOM_FAST = R.anim.slide_in_bottom_fast;
    public static final int SCALE_UP = R.anim.scale_up;
    public static final int SCALE_DOWN = R.anim.scale_down;
    public static final int FADE_IN_FAST = R.anim.fade_in_fast;
    public static final int FADE_OUT_FAST = R.anim.fade_out_fast;
    public static final int SLIDE_OUT_DOWN = R.anim.slide_out_down;
    public static final int FADE_IN = R.anim.fade_in;
    public static final int FADE_OUT = R.anim.fade_out;
    public static final int STRETCH_TO_FIT_WIDTH = R.anim.stretch_to_fit_width;


    public static void setAnimation(@AnimRes int animId, View... views) {
        Context context = views[0].getContext();
        Animation animation = AnimationUtils.loadAnimation(context, animId);
        animation.setFillAfter(true);
        for (View v : views)
            v.startAnimation(animation);
    }
}
