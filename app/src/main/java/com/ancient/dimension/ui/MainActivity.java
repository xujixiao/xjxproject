package com.ancient.dimension.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ancient.dimension.R;
import com.ancient.dimension.databinding.ActMainBinding;
import com.ancient.dimension.ui.presenter.TestPresenter;
import com.base.lib.base.BaseActivity;
import com.base.lib.base.BasePresenter;
import com.base.lib.base.SActivity;


public class MainActivity extends SActivity<TestPresenter, ActMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected TestPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }
}
