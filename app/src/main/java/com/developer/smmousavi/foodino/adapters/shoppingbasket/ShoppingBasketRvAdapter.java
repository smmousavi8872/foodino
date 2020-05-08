package com.developer.smmousavi.foodino.adapters.shoppingbasket;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.callback.EmptyBasketCallback;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.callback.UpdateBasketContentCallback;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.ui.viewholders.shoppingbasket.ShoppingBasketVH;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingBasketRvAdapter extends RecyclerView.Adapter<ShoppingBasketVH> {

    public static final int DELAY_INTERVAL = 100;

    @Inject
    List<Product> mItemList;

    private EmptyBasketCallback mEmptyListCallback;
    private UpdateBasketContentCallback mUpdateListCallback;
    private FragmentManager mFm;


    public void setFm(FragmentManager fm) {
        mFm = fm;
    }

    public void setItemList(List<Product> itemList) {
        mItemList = itemList;
    }

    public void setEmptyListCallback(EmptyBasketCallback callback) {
        mEmptyListCallback = callback;
    }

    public void setUpdateListCallback(UpdateBasketContentCallback callback) {
        mUpdateListCallback = callback;

    }

    public void emptyItemList() {

        long totalDelay = mItemList.size() * (DELAY_INTERVAL + 1);

        new Handler().postDelayed(() -> {
            if (mItemList.size() > 0) {
                mItemList.subList(0, mItemList.size()).clear();
            }
            notifyDataSetChanged();
        }, totalDelay);

        new Handler().postDelayed(() -> mEmptyListCallback.onEmptyFinished(), totalDelay + DELAY_INTERVAL);
    }


    @NonNull
    @Override
    public ShoppingBasketVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shopping_basket, null, false);
        return new ShoppingBasketVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingBasketVH holder, int position) {
        holder.bind(mItemList.get(position), mFm);
        holder.setUpdateBasketContentCallback(mUpdateListCallback);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public void removeItem(int position) {
        mItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItemList.size());
    }


}
