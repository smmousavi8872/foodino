package com.developer.smmousavi.foodino.base.recyclerview;

import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int HEADER = 0;
    protected static final int ITEM = 1;
    protected static final int FOOTER = 2;

    protected List<T> mItemList;
    protected OnItemClickListener mOnItemClickListener;
    protected OnReloadClickListener mOnReloadClickListener;
    protected boolean mIsFooterAdded = false;

    protected abstract RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent);

    protected abstract RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent);

    protected abstract RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent);

    protected abstract void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder);

    protected abstract void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    protected abstract void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder);

    protected abstract void displayLoadMoreFooter();

    protected abstract void displayErrorFooter();

    protected abstract boolean hasFooter();

    public abstract void addFooter();

    public enum FooterType {
        LOAD_MORE,
        ERROR
    }

    public void setItemList(List<T> items) {
        mItemList = items;
        notifyDataSetChanged();
    }

    public List<T> getItemList() {
        return mItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case HEADER:
                viewHolder = createHeaderViewHolder(parent);
                break;
            case ITEM:
                viewHolder = createItemViewHolder(parent);
                break;
            case FOOTER:
                viewHolder = createFooterViewHolder(parent);
                break;
            default:
                viewHolder = createItemViewHolder(parent);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                bindHeaderViewHolder(viewHolder);
                break;
            case ITEM:
                bindItemViewHolder(viewHolder, position);
                break;
            case FOOTER:
                bindFooterViewHolder(viewHolder);
                break;
            default:
                bindItemViewHolder(viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        if (mItemList != null)
            return mItemList.size();
        return 0;
    }

    public T getItem(int position) {
        return mItemList.get(position);
    }

    public void add(T item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.size() - 1);
    }

    public void addAll(List<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    private void remove(T item) {
        int position = mItemList.indexOf(item);
        if (position > -1) {
            mItemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        mIsFooterAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public boolean isLastPosition(int position) {
        return (position == mItemList.size() - 1);
    }

    public void removeFooter() {
        mIsFooterAdded = false;

        int position = mItemList.size() - 1;
        T item = getItem(position);

        if (item != null) {
            mItemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateFooter(FooterType footerType) {
        switch (footerType) {
            case LOAD_MORE:
                displayLoadMoreFooter();
                break;
            case ERROR:
                displayErrorFooter();
                break;
            default:
                break;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnReloadClickListener(OnReloadClickListener onReloadClickListener) {
        mOnReloadClickListener = onReloadClickListener;
    }
}
