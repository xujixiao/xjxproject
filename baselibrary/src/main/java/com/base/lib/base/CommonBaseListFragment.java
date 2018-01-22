package com.base.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.lib.base.entity.ICommonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2011-2017
 * FileName: com.ancient.dimension.ui.message.activity.CallMeActivity.java
 * Author: xujixiao
 * Date: 2017/12/25 15:14
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      15:14    1.0        Create
 */
public abstract class CommonBaseListFragment<P extends BasePresenter> extends BaseListFragment<P> {
    private List<ICommonEntity> mEntityList = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addData();
    }

    @Override
    protected void addData() {
        super.addData();

    }


    @Override
    public void refreshNetData() {

    }

    @Override
    public void loadMoreData() {

    }

}
