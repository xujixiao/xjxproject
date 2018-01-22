package com.base.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by kec on 2017/8/14.
 */

public abstract class BasePresenterFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

}
