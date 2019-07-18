package com.ancient.dimension.ui;

import com.base.lib.BaseApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 11073 on 2018/1/30.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }
}
