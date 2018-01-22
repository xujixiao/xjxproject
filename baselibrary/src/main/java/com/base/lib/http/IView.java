package com.base.lib.http;

import com.base.lib.base.BaseActivity;

/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.http.IView.java
 * Author: xujixiao
 * Date: 2017/12/25 13:58
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      13:58    1.0        Create
 */
public interface IView {
    void finishCurrent();

    void showProgress();

    void dismissProgress();

    BaseActivity obtainActivity();

    void handleError(Throwable throwable);

}
