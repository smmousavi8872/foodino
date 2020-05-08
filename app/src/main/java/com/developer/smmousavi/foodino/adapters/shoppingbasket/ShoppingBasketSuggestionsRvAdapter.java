package com.developer.smmousavi.foodino.adapters.shoppingbasket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.ui.viewholders.shoppingbasket.ShoppingBasketSuggestionsVH;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingBasketSuggestionsRvAdapter extends RecyclerView.Adapter<ShoppingBasketSuggestionsVH> {

    private List<Recipe> mItemList;

    public void setItemList(List<Recipe> itemList) {
        mItemList = itemList;
        notifyDataSetChanged();
    }

    public void emptyItemList() {
        if (mItemList != null && mItemList.size() > 0) {
            mItemList.subList(0, mItemList.size()).clear();
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoppingBasketSuggestionsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_preview, parent, false);
        return new ShoppingBasketSuggestionsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingBasketSuggestionsVH holder, int position) {
        holder.onBind(mItemList.get(position));

    }

    @Override
    public int getItemCount() {
        if (mItemList != null)
            return mItemList.size();
        return 0;
    }
}
