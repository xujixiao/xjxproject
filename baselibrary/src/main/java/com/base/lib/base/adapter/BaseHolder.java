package com.base.lib.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.lib.base.listener.IItemClick;

public class BaseHolder<E extends IHolderType, B extends ViewDataBinding>
        extends RecyclerView.ViewHolder {

    private IItemClick<E, B> mEBIItemClick;
    private B binding;
    private IHandleHolder<E, B> mIHandleHolder;

    public void setItemClick(IItemClick itemClick) {
        mEBIItemClick = itemClick;
    }

    public BaseHolder<E, B> installHandleHolder(IHandleHolder<E, B> iHandleHolder) {
        mIHandleHolder = iHandleHolder;
        return this;
    }

    public BaseHolder(ViewGroup viewGroup, int layoutId) {
        super(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false).getRoot());
        binding = DataBindingUtil.getBinding(this.itemView);
    }


    public void onBaseBindViewHolder(E entity, final int position) {
        if (mIHandleHolder != null) {
            mIHandleHolder.handleHolder(entity, position, binding);
        }
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEBIItemClick != null) {
                    mEBIItemClick.callBack(entity, binding, position);
                }
            }
        });
        binding.executePendingBindings();
    }

}
