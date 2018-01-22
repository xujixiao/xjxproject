package com.base.lib.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.base.lib.R;
import com.base.lib.base.adapter.BaseHolder;
import com.base.lib.base.adapter.BaseRecycleAdapter;
import com.base.lib.base.adapter.IHandleHolder;
import com.base.lib.base.adapter.IHolderType;
import com.base.lib.base.adapter.IViewHolder;
import com.base.lib.base.listener.IItemClick;
import com.base.lib.databinding.CommonListLayoutBinding;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.base.BaseListActivity.java
 * Author: xujixiao
 * Date: 2017/12/4 15:36
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      15:36    1.0        Create
 */
public abstract class BaseListActivity<P extends BasePresenter> extends SActivity<P, CommonListLayoutBinding> implements IViewHolder, IItemClick {

    protected BaseRecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
    }

    /**
     * 添加测试数据的方法
     */
    protected void addData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_list_layout;
    }

    private void initAdapter() {
        mAdapter = new BaseRecycleAdapter();
        mAdapter.installViewHolder(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.xrv.setLayoutManager(layoutManager);
        mBinding.xrv.setAdapter(mAdapter);
        mBinding.xrv.setLoadingMoreEnabled(true);
        mBinding.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshNetData();
                mBinding.xrv.postDelayed(() -> mBinding.xrv.refreshComplete(), 2000);
            }

            @Override
            public void onLoadMore() {
                loadMoreData();
                addData();
                mBinding.xrv.postDelayed(() -> mBinding.xrv.loadMoreComplete(), 2000);
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    public abstract int getItemLayoutId(int viewType);

    @Override
    public BaseHolder CreateViewHolder(ViewGroup parent, int viewType) {
        return getBaseHolder(parent, getItemLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        bindViewHolder(holder, position);
    }

    @Override
    protected P createPresenter() {
        return null;
    }

    public abstract void refreshNetData();

    public abstract void loadMoreData();

    public <E extends IHolderType, B extends ViewDataBinding> BaseHolder<E, B> getBaseHolder(ViewGroup parent, int layoutId) {
        BaseHolder baseHolder = new BaseHolder<E, B>(parent, layoutId);
        baseHolder.installHandleHolder(new IHandleHolder<E, B>() {

            @Override
            public void handleHolder(E entity, int position, B binding) {
                bindData(entity, position, binding);
            }
        });
        baseHolder.setItemClick(this);
        return baseHolder;
    }

    public abstract <E extends IHolderType, B extends ViewDataBinding> void bindData(E entity, int position, B binding);


    public <E extends IHolderType, B extends ViewDataBinding> void bindViewHolder(BaseHolder baseHolder, int position) {
        baseHolder.onBaseBindViewHolder((E) mAdapter.getData().get(position), position);
    }


    @Override
    public void callBack(IHolderType entity, ViewDataBinding binding, int position) {

    }
}
