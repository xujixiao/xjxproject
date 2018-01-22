package com.base.lib.base.constants;

/**
 * Created by 11073 on 2018/1/9.
 */

public class CustomException  {
    public int code;
    public Throwable mThrowable;

    public CustomException(int code, Throwable throwable) {
        this.code = code;
        mThrowable = throwable;
    }
}
