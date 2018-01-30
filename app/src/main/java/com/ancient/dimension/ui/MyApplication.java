package com.ancient.dimension.ui;

import com.base.lib.BaseApplication;

/**
 * Created by 11073 on 2018/1/30.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void initParamsMap() {
        super.initParamsMap();
        paramsMap.put(BUGLY_APP_ID, "ee1807ef5c");
        paramsMap.put(BUGLY_APP_SECRET, "b5d6d308-3ad6-4e36-b36d-021d178ac8dc");
        paramsMap.put(BUGLY_DEBUG, "true");
        paramsMap.put(AROUTE_DEBUG, "true");
    }
}
