package com.developer.smmousavi.foodino.adapters.mapsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.base.recyclerview.BaseRvAdapter;
import com.developer.smmousavi.foodino.ui.fragments.mapsearch.AddressSelectCallback;
import com.developer.smmousavi.foodino.network.mapresponse.Value;
import com.developer.smmousavi.foodino.ui.viewholders.mapsearch.MapSearchVH;

import androidx.recyclerview.widget.RecyclerView;

public class MapSearchRvAdapter<T extends Value> extends BaseRvAdapter<T> {

    private AddressSelectCallback mAddressSelectCallback;

    public MapSearchRvAdapter( AddressSelectCallback addressSelectCallback) {
        mAddressSelectCallback = addressSelectCallback;
    }


    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_map_search_result, parent, false);
        return new MapSearchVH(v);
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_map_search_result, parent, false);
        return new MapSearchVH(v);
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {
        ((MapSearchVH) viewHolder).onBind(mItemList.get(0), mAddressSelectCallback);
    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((MapSearchVH) viewHolder).onBind(mItemList.get(position), mAddressSelectCallback);
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void displayLoadMoreFooter() {

    }

    @Override
    protected void displayErrorFooter() {

    }

    @Override
    protected boolean hasFooter() {
        return false;
    }

    @Override
    public void addFooter() {

    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }
}
