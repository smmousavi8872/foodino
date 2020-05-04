package com.developer.smmousavi.foodino.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mapbox.mapboxsdk.maps.MapboxMapOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.map.sdk_map.maps.MapView;

public class CustomMapView extends MapView {

    public CustomMapView(@NonNull Context context) {
        super(context);
    }

    public CustomMapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomMapView(@NonNull Context context, @Nullable MapboxMapOptions options) {
        super(context, options);
    }

    // this method solves the problem of inserting map view inside a scrollview
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Disallow ScrollView to intercept touch events.
                this.getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_UP:
                // Allow ScrollView to intercept touch events.
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        // Handle MapView's touch events.
        super.onTouchEvent(ev);
        return true;
    }
}
