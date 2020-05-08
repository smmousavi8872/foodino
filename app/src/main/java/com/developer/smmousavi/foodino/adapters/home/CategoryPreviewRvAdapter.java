package com.developer.smmousavi.foodino.adapters.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.ui.viewholders.categorypreview.CategoryPreviewVH;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryPreviewRvAdapter extends RecyclerView.Adapter<CategoryPreviewVH> {

    private List<Category> mCategoryList;


    public CategoryPreviewRvAdapter(List<Category> categories) {
        this.mCategoryList = categories;
    }

    public void setItemList(List<Category> categoryList) {
        mCategoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryPreviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_preview, null);
        return new CategoryPreviewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryPreviewVH holder, int position) {
        holder.onBind(mCategoryList.get(position));

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
