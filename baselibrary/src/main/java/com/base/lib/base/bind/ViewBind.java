package com.base.lib.base.bind;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Copyright (C), 2011-2017
 * .utils.bind.ViewBind.java
 *
 *
 * Date: 2017/6/29 16:53
 * Description:
 * History:
 *
 * xujixiao      16:53    1.0        Create
 */
public class ViewBind {
    @BindingAdapter("android:visible")
    public static void setVisible(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("android:inVisible")
    public static void setInVisible(View view, boolean isInVisible) {
        view.setVisibility(isInVisible ? View.INVISIBLE : View.VISIBLE);
    }
}
