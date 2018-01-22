package com.base.lib.utils;

import com.base.lib.BaseApplication;
import com.base.lib.tools.ToastUtils;

/**
 * Created by 11073 on 2018/1/9.
 */

public class MyToast {
    public static void show(String str) {
        ToastUtils.showShortToast(BaseApplication.context, str);
    }
}
