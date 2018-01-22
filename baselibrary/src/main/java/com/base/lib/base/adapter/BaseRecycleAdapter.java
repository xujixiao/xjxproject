package com.base.lib.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseRecycleAdapter<T extends IHolderType, K extends BaseHolder> extends RecyclerView.Adapter<K> {

    private List<T> mList = new ArrayList<>();
    private IViewHolder mIViewHolder;

    public void installViewHolder(IViewHolder iViewHolder) {
        mIViewHolder = iViewHolder;
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        if (mIViewHolder != null) {
            mIViewHolder.onBindViewHolder(holder, position);
        } else {
            throw new RuntimeException("请设置viewholder的接口");
        }
    }


    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mIViewHolder != null) {
            return (K) mIViewHolder.CreateViewHolder(parent, viewType);
        } else {
            throw new RuntimeException("请设置viewholder的接口");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() > position) {
            IHolderType iHolderType = mList.get(position);
            return iHolderType.holderType();
        }
        return 0;
    }

    /**
     * 增加数据
     *
     * @param data
     */
    public void loadMoreData(List<T> data) {
        if (data == null) return;
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 清除原有数据后重新添加
     *
     * @param data
     */
    public void refreshData(List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    public void add(T object) {
        if (object == null) return;
        mList.add(object);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void remove(T object) {
        if (object == null) return;
        mList.remove(object);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (position >= mList.size()) return;
        mList.remove(position);
        notifyDataSetChanged();
    }

    public void removeAll(List<T> data) {
        if (data == null) return;
        this.mList.retainAll(data);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mList;
    }

}
