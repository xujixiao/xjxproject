package com.base.lib.base;


import android.content.Context;

import com.base.lib.bean.BaseBean;
import com.base.lib.http.Api;
import com.base.lib.http.IView;
import com.base.lib.http.ResultListener;
import com.base.lib.tools.DateUtils;
import com.base.lib.tools.NetHelper;
import com.base.lib.tools.PreferenceUtils;
import com.base.lib.tools.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kec on 2017/8/10.
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


    public void requestWithUserId(HashMap<String, String> map, String cmd, ResultListener<BaseBean> resultListener) {
        map.put("userid", PreferenceUtils.getPrefString(((IView) mvpView).obtainActivity(), "user_id", ""));
        request(map, cmd, resultListener);
    }

    public void request(HashMap<String, String> map, String cmd, ResultListener<BaseBean> resultListener) {
        request(map, cmd, new io.reactivex.functions.Consumer<BaseBean>() {
            @Override
            public void accept(BaseBean baseBean) throws Exception {
                if (mvpView != null) {
                    IView iView = (IView) mvpView;
                    iView.dismissProgress();
                    if (baseBean.status.equals("true")) {
                        resultListener.onResponse(baseBean);
                    } else {
                        resultListener.onError(new Exception(baseBean.msg));
                        iView.handleError(new Exception(baseBean.getMsg()));
                    }
                }
            }
        }, new io.reactivex.functions.Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                /*不需要处理error的可以不写error*/
                if (mvpView != null) {
                    IView iView = (IView) mvpView;
                    iView.handleError(throwable);
                    iView.dismissProgress();
                    resultListener.onError(throwable);
                }
            }
        });
    }

    /**
     * 上传带数组的附件请求
     *
     * @param map
     * @param attachment
     * @param cmd
     * @param resultListener
     */
    public void requestWithArray(HashMap<String, String> map, List<String> attachment, String cmd, ResultListener<BaseBean> resultListener) {
        requestWithArray(map, attachment, cmd, new io.reactivex.functions.Consumer<BaseBean>() {
            @Override
            public void accept(BaseBean baseBean) throws Exception {
                if (mvpView != null) {
                    IView iView = (IView) mvpView;
                    iView.dismissProgress();
                    if (baseBean.status.equals("true")) {
                        resultListener.onResponse(baseBean);
                    } else {
                        resultListener.onError(new Exception(baseBean.msg));
                        iView.handleError(new Exception(baseBean.getMsg()));
                    }
                }
            }
        }, new io.reactivex.functions.Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                /*不需要处理error的可以不写error*/
                if (mvpView != null) {
                    IView iView = (IView) mvpView;
                    iView.handleError(throwable);
                    iView.dismissProgress();
                    resultListener.onError(throwable);
                }
            }
        });
    }

    private void requestWithArray(HashMap<String, String> map, List<String> attachment, String cmd, io.reactivex.functions.Consumer<BaseBean> consumer, io.reactivex.functions.Consumer<Throwable> error) {
        BaseActivity activity = ((IView) mvpView).obtainActivity();
        if (!checkNetwork(activity)) {
            ToastUtils.showShortToast(activity, NETWORK_FAILURE);
        }
        map.put("version", "1.0.0");
        map.put("timestamp", DateUtils.format(System.currentTimeMillis()));
        map.put("cmd", cmd);
        Api.requestWithArrat(activity, map, attachment, consumer, error);
    }

    private void request(HashMap<String, String> map, String cmd, io.reactivex.functions.Consumer<BaseBean> consumer, io.reactivex.functions.Consumer<Throwable> error) {
        BaseActivity activity = ((IView) mvpView).obtainActivity();
        if (!checkNetwork(activity)) {
            ToastUtils.showShortToast(activity, NETWORK_FAILURE);
        }
        map.put("version", "1.0.0");
        map.put("timestamp", DateUtils.format(System.currentTimeMillis()));
        map.put("cmd", cmd);
        Api.request(activity, map, consumer, error);
    }


    private void upload(File file, String cmd, String userId, io.reactivex.functions.Consumer<BaseBean> consumer, io.reactivex.functions.Consumer<Throwable> error) {
        BaseActivity activity = ((IView) mvpView).obtainActivity();
        if (!checkNetwork(activity)) {
            ToastUtils.showShortToast(activity, NETWORK_FAILURE);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.0.0");
        map.put("userid", userId);
        map.put("timestamp", DateUtils.format(System.currentTimeMillis()));
        map.put("cmd", cmd);
        Api.upload(activity, map, file, consumer, error);
    }


    public void upload(File file, String cmd, String userId, ResultListener<BaseBean> resultListener) {
        upload(file, cmd, userId, new io.reactivex.functions.Consumer<BaseBean>() {
            @Override
            public void accept(BaseBean baseBean) throws Exception {
                if (mvpView != null) {
                    IView iView = (IView) mvpView;
                    iView.dismissProgress();
                    if (baseBean.status.equals("true")) {
                        resultListener.onResponse(baseBean);
                    } else {
                        resultListener.onError(new Exception(baseBean.msg));
                        iView.handleError(new Exception(baseBean.getMsg()));
                    }
                }
            }
        }, new io.reactivex.functions.Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                /*不需要处理error的可以不写error*/
                if (mvpView != null) {
                    IView iView = (IView) mvpView;
                    iView.handleError(throwable);
                    iView.dismissProgress();
                    resultListener.onError(throwable);
                }
            }
        });
    }


}
