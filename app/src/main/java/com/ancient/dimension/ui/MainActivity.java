package com.ancient.dimension.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ancient.dimension.R;
import com.base.lib.base.BaseActivity;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.act_webview_layout);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

}
