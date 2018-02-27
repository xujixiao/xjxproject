package com.base.lib.callback;

/**
 * Created by 11073 on 2018/1/9.
 */

public interface ResultListener<T> {
    public void onResponse(T t);

    void onError(Throwable throwable);
}
