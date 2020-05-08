package com.developer.smmousavi.foodino.adapters.specialoffers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.base.recyclerview.BaseRvAdapter;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.ui.viewholders.LoadMoreVH;
import com.developer.smmousavi.foodino.ui.viewholders.specialoffers.SpecialOffersVH;

import androidx.recyclerview.widget.RecyclerView;


public class SpecialOffersRvAdapter<T extends Recipe> extends BaseRvAdapter<T> {

    private boolean mQueryExhausted;

    public boolean isQueryExhausted() {
        return mQueryExhausted;
    }

    public void setQueryExhausted(boolean queryExhausted) {
        mQueryExhausted = queryExhausted;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_special_offer_product_small, parent, false);
        return new SpecialOffersVH(v);
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rv_load_more_footer, parent, false);
        return new LoadMoreVH(v);
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {
    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((SpecialOffersVH) viewHolder).onBind(mItemList.get(position));

    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        ((LoadMoreVH) viewHolder).onBind(null);

    }

    @Override
    protected void displayLoadMoreFooter() {

    }

    @Override
    protected void displayErrorFooter() {

    }

    @Override
    protected boolean hasFooter() {
        return true;
    }

    @Override
    public void addFooter() {
        if (mItemList != null) {
            Recipe recipe = new Recipe();
            recipe.setTitle("Loading ... ");
            mItemList.add((T) recipe);
            notifyItemInserted(mItemList.size() - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mItemList.size() - 1 && !mQueryExhausted) {
            return FOOTER;
        }
        return ITEM;
    }
}
