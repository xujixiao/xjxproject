package com.base.lib.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.lib.R;
import com.base.lib.base.viewentity.ExceptionViewEntity;
import com.base.lib.base.viewentity.TitleViewEntity;
import com.base.lib.databinding.CommonLayoutBinding;


/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.base.SActivity.java
 * Author: xujixiao
 * Date: 2017/12/25 10:32
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      10:32    1.0        Create
 */
public abstract class SFragment<P extends BasePresenter, B extends ViewDataBinding> extends BaseFragment {
    protected B mBinding;
    protected P mvpPresenter;
    protected TitleViewEntity mTitleViewEntity;
    protected ExceptionViewEntity mExceptionViewEntity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);

        CommonLayoutBinding commonLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.common_layout, container, false);
        commonLayoutBinding.llParent.setClickable(true);
        mTitleViewEntity = new TitleViewEntity();
        initTitle();
        mExceptionViewEntity = new ExceptionViewEntity();
        mExceptionViewEntity.imageId.set(R.mipmap.ic_launcher);
        mExceptionViewEntity.text.set("网络无信号");
        commonLayoutBinding.setTitleViewEntity(mTitleViewEntity);
        commonLayoutBinding.setExceptionViewEntity(mExceptionViewEntity);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), getLayoutId(), null, false);
        commonLayoutBinding.flContent.addView(mBinding.getRoot());
        initBind();
        return commonLayoutBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mvpPresenter != null) {
            mvpPresenter.attachView(this);
        }
    }

    protected abstract P createPresenter();

    protected void initBind() {

    }

    protected void initTitle() {
        mTitleViewEntity.titleLayoutVisible.set(true);
        mTitleViewEntity.titleLeftClick.set(v -> finishCurrent());
        mTitleViewEntity.rightLayoutVisible.set(false);
        mTitleViewEntity.backIconVisible.set(true);
        mTitleViewEntity.title.set("测试标题");
    }

    public void finishCurrent() {
        if (getActivity() instanceof SActivity) {
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detechView(this);
        }
    }

    protected abstract int getLayoutId();
}
