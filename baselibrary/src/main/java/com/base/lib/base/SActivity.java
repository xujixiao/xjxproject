package com.base.lib.base;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import com.base.lib.R;
import com.base.lib.base.entity.ChangeAnimType;
import com.base.lib.base.viewentity.ExceptionViewEntity;
import com.base.lib.base.viewentity.TitleViewEntity;
import com.base.lib.databinding.CommonLayoutBinding;
import com.base.lib.utils.CheckDoubleUtils;
import com.base.lib.utils.InputMethodUtil;

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
public abstract class SActivity<P extends BasePresenter, B extends ViewDataBinding> extends BaseActivity {
    protected B mBinding;
    protected P mvpPresenter;
    protected TitleViewEntity mTitleViewEntity;
    protected ExceptionViewEntity mExceptionViewEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
        if (mvpPresenter != null) {
            mvpPresenter.attachView(this);
        }
        CommonLayoutBinding commonLayoutBinding = DataBindingUtil.setContentView(this, R.layout.common_layout);
        mTitleViewEntity = new TitleViewEntity();
        initTitle();
        mExceptionViewEntity = new ExceptionViewEntity();
        mExceptionViewEntity.imageId.set(R.mipmap.ic_launcher);
        mExceptionViewEntity.text.set("网络无信号");
        commonLayoutBinding.setTitleViewEntity(mTitleViewEntity);
        commonLayoutBinding.setExceptionViewEntity(mExceptionViewEntity);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutId(), null, false);
        commonLayoutBinding.flContent.addView(mBinding.getRoot());
        mFragmentManager = getSupportFragmentManager();
        initBind();
    }

    protected SFragment getFragment() {
        return null;
    }

    protected abstract P createPresenter();

    protected void initBind() {

    }

    public void addFragment(SFragment sFragment) {
        addFragment(sFragment, R.id.fl_content, ChangeAnimType.LEFT_RIGHT);
    }

    public void addFragment(SFragment sFragment, int anim) {
        addFragment(sFragment, R.id.fl_content, anim);
    }

    public void addFragment(SFragment sFragment, int layoutId, int anim) {
        if (CheckDoubleUtils.isFastDoubleClick()) {
            return;
        }
        if (mFragmentManager != null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            setAnimation(anim, fragmentTransaction);
            fragmentTransaction.add(layoutId, sFragment)
                    .addToBackStack(sFragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }

    public void replaceFragment(SFragment sFragment, int layoutId, int anim) {
        if (CheckDoubleUtils.isFastDoubleClick()) {
            return;
        }
        if (mFragmentManager != null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            setAnimation(anim, fragmentTransaction);
            fragmentTransaction.replace(layoutId, sFragment)
                    .addToBackStack(sFragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }

    private void setAnimation(int animType, FragmentTransaction transaction) {
        if (animType == ChangeAnimType.LEFT_RIGHT) {
            transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_from_left, R.anim.in_from_left, R.anim.out_from_right);
        } else if (animType == ChangeAnimType.SCALE) {
            transaction.setCustomAnimations(R.anim.scale_to_one, R.anim.scale_to_zero);
        } else if (animType == ChangeAnimType.TOP_TO_LOW) {
            transaction.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_top, R.anim.slide_in, R.anim.slide_out_to_top);
        } else if (animType == ChangeAnimType.LOW_TO_TOP_) {
            transaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_in, R.anim.slide_out_to_bottom);
        }
    }

    protected void initTitle() {
        mTitleViewEntity.titleLayoutVisible.set(true);
        mTitleViewEntity.titleLeftClick.set(v -> finish());
        mTitleViewEntity.rightLayoutVisible.set(false);
        mTitleViewEntity.backIconVisible.set(true);
        mTitleViewEntity.title.set("测试");
    }


    @SuppressLint("RestrictedApi")
    protected void removeFragment() {
        showKeyBoard(false);
        if (mFragmentManager.getFragments() != null && mFragmentManager.getFragments().size() > 0) {
            if (mFragmentManager.getBackStackEntryCount() > 1) {
                mFragmentManager.popBackStack();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        showKeyBoard(false);
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detechView(this);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            removeFragment();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void showKeyBoard(boolean flag) {
        if (flag) {
            InputMethodUtil.showSoftInput(this);
        } else
            InputMethodUtil.hideSoftInput(this);
    }

    @Override
    public void onBackPressed() {
        removeFragment();
    }

    protected abstract int getLayoutId();
}
