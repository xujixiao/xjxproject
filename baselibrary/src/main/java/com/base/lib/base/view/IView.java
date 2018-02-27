package com.base.lib.base.view;

import com.base.lib.base.BaseActivity;

/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.base.view.IView.java
 *
 * Date: 2017/12/25 13:58
 * Description:
 * History:
 *
 * xujixiao      13:58    1.0        Create
 */
public interface IView {
    void finishCurrent();

    void showProgress();

    void dismissProgress();

    BaseActivity obtainActivity();

    void handleError(Throwable throwable);

}
