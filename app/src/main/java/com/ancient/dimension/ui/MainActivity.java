package com.ancient.dimension.ui;

import android.os.Bundle;
import android.view.View;

import com.ancient.dimension.R;
import com.ancient.dimension.databinding.ActMainBinding;
import com.ancient.dimension.ui.presenter.TestPresenter;
import com.base.lib.base.SActivity;


public class MainActivity extends SActivity<TestPresenter, ActMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding.tvBuglyTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestListActivity.start(MainActivity.this);
            }
        });
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
