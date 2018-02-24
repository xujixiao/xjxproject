package com.base.lib.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.base.lib.databinding.SmartCommonListLayoutBinding;
import com.base.lib.utils.RecycleViewDivider;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.base.BaseListActivity.java
 * <p>
 * Date: 2017/12/4 15:36
 * Description:
 * History:
 * <p>
 * xujixiao      15:36    1.0        Create
 */
public abstract class BaseListFragment<P extends BasePresenter> extends SFragment<P, SmartCommonListLayoutBinding> implements IViewHolder, IItemClick {

    protected BaseRecycleAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdapter();
        if (showRecycleViewDecoration()) {
            mBinding.rcv.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.basic_dialog));
        }
    }

    protected boolean showRecycleViewDecoration() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.smart_common_list_layout;
    }

    private void initAdapter() {
        mAdapter = new BaseRecycleAdapter();
        mAdapter.installViewHolder(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mBinding.rcv.setLayoutManager(layoutManager);
        mBinding.rcv.setAdapter(mAdapter);

        mBinding.srl.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshNetData();
            }
        });
        mBinding.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadMoreData();
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    public abstract int getItemLayoutId(int viewType);

    public void loadDataComplete() {
        try {
            mBinding.srl.finishRefresh();
            mBinding.srl.finishLoadMore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callBack(IHolderType entity, ViewDataBinding binding, int position) {

    }

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


}
