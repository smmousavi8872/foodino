package com.developer.smmousavi.foodino.helper;

import android.content.Context;
import android.view.View;

import com.developer.smmousavi.foodino.builder.recyclerview.RecyclerViewBuilder;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHelper {

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public RecyclerView buildRecyclerView(RecyclerView.LayoutManager layoutManager,
                                          RecyclerView recyclerView,
                                          RecyclerView.Adapter adapter) {
        new RecyclerViewBuilder(recyclerView)
            .setLayoutManager(layoutManager)
            .setItemViewCacheSize(30)
            .setHasFixedSize(true)
            .setDrawingCacheEnabled(true)
            .setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH)
            .setAdapter(adapter)
            .build();
        return recyclerView;
    }


    public LinearLayoutManager getLinearLayoutManager(Context context, Orientation orientation, boolean reverseLayout) {
        LinearLayoutManager layoutManager;
        switch (orientation) {
            case VERTICAL:
                layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, reverseLayout);
                break;
            case HORIZONTAL:
                layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, reverseLayout);
                break;
            default:
                layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, reverseLayout);
        }
        return layoutManager;
    }

    public GridLayoutManager getGridLayoutManager(Context context, Orientation orientation, int spanCount) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, spanCount);
        switch (orientation) {
            case VERTICAL:
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                break;
            case HORIZONTAL:
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                break;
            default:
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);


        }
        return layoutManager;
    }
}
