package com.base.lib.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.base.lib.R;
import com.base.lib.base.entity.ICommonEntity;
import com.base.lib.databinding.CommonLayoutBinding;
import com.base.lib.databinding.CommonListLayoutBinding;
import com.base.lib.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2011-2017
 * FileName: com.ancient.dimension.ui.message.activity.CallMeActivity.java
 *
 * Date: 2017/12/25 15:14
 * Description:
 * History:
 *
 * xujixiao      15:14    1.0        Create
 */
public abstract class CommonBaseListActivity<P extends BasePresenter,B extends CommonListLayoutBinding> extends BaseListActivity<P,B> {
    private List<ICommonEntity> mEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (showRecycleViewDecoration()) {
            mBinding.xrv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, R.drawable.ic_loading_rotate));
        }
        addData();
    }


    /**
     * 是否显示recycleview的分割线
     *
     * @return
     */
    protected boolean showRecycleViewDecoration() {
        return true;
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
