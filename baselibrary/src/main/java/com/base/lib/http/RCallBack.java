package com.base.lib.http;

/**
 * Created by 11073 on 2018/1/9.
 */

public abstract class RCallBack<T> implements ResultListener<T> {
    @Override
    public void onResponse(T t) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
