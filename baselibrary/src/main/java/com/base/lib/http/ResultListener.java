package com.base.lib.http;

import com.base.lib.base.constants.CustomException;

/**
 * Created by 11073 on 2018/1/9.
 */

public interface ResultListener<T> {
    public void onResponse(T t);

    void onError(Throwable throwable);
}
