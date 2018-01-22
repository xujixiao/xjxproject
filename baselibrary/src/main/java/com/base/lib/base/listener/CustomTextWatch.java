package com.base.lib.base.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by 11073 on 2018/1/8.
 * 自一定文本框监听的类，避免重写不必要的方法
 */

public class CustomTextWatch implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
