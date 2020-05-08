package com.developer.smmousavi.foodino.adapters.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.ui.viewholders.productpreview.ProductPreviewVH;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductPreviewRvAdapter extends RecyclerView.Adapter<ProductPreviewVH> {

    private List<Recipe> mItemList;

    public void setItemList(List<Recipe> itemList) {
        this.mItemList = itemList;
        notifyDataSetChanged();
    }

    public ProductPreviewRvAdapter() {

    }

    @NonNull
    @Override
    public ProductPreviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_preview, null);
        return new ProductPreviewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductPreviewVH holder, int position) {
        holder.onBind(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
