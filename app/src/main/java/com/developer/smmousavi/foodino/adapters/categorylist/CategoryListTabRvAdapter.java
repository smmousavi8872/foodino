package com.developer.smmousavi.foodino.adapters.categorylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.viewholders.categorylisttab.CategoryListTabVH;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListTabRvAdapter extends RecyclerView.Adapter<CategoryListTabVH> {

    private List<Category> mItemList;

    public void setItemList(List<Category> itemList) {

        mItemList = itemList;
    }

    public CategoryListTabRvAdapter(List<Category> itemList) {
        mItemList = itemList;
    }

    @NonNull
    @Override
    public CategoryListTabVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categoy_list, null, false);
        return new CategoryListTabVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListTabVH holder, int position) {
        holder.bind(mItemList.get(position));

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
