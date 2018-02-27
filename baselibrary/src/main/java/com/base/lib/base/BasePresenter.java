package com.base.lib.base;


import android.content.Context;

import com.base.lib.tools.NetHelper;

/**
 */

public class BasePresenter<T> {

    public T mvpView;

    protected static final int TONKEN = 2004;//Token无效，或已超时 需重新登录

    protected static final String NETWORK_FAILURE = "未连接到网络, 请稍候再试...";

    protected static final String REQUEST_FAILURE = "数据请求出错, 请稍候再试...";

    protected static final String RESPONSE_FAILURE = "数据返回出错, 请稍候再试...";

    protected static final String RESPONSE_GSON_FAILURE = "数据解析出错, 请稍候再试...";


    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    public void detechView(T mvpView) {
        this.mvpView = null;
    }

    /**
     * 检测网络状态
     *
     * @param context
     * @return
     */
    protected boolean checkNetwork(Context context) {
        return NetHelper.IsHaveInternet(context);
    }

}
