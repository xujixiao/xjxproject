package com.base.lib.tools;

import android.widget.Toast;

import com.base.lib.BaseApplication;

public class ToastUtils {

    public static void showShortToast(String content) {
        Toast.makeText(BaseApplication.context, content, Toast.LENGTH_SHORT).show();
    }
}
