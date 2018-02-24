package com.ancient.dimension.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.ancient.dimension.R;
import com.ancient.dimension.databinding.TestListItemLayoutBinding;
import com.ancient.dimension.ui.entity.ListTestEntity;
import com.ancient.dimension.ui.presenter.TestPresenter;
import com.base.lib.base.BaseListActivity;
import com.base.lib.base.adapter.IHolderType;
import com.base.lib.databinding.SmartCommonListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11073 on 2018/2/24.
 */

public class TestListActivity extends BaseListActivity<TestPresenter, SmartCommonListLayoutBinding> {

    private List mList = new ArrayList();

    public static void start(Context context) {
        Intent starter = new Intent(context, TestListActivity.class);
//    starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList.add(new ListTestEntity());
        mList.add(new ListTestEntity());
        mList.add(new ListTestEntity());
        mList.add(new ListTestEntity());
        mList.add(new ListTestEntity());
        mList.add(new ListTestEntity());
        mAdapter.refreshData(mList);

    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.test_list_item_layout;
    }

    @Override
    public void refreshNetData() {
        toast("刷新完成");
        loadDataComplete();
    }

    @Override
    public void loadMoreData() {
        loadDataComplete();
    }

    @Override
    public <E extends IHolderType, B extends ViewDataBinding> void bindData(E entity, int position, B binding) {
        if (binding instanceof TestListItemLayoutBinding) {
            ((TestListItemLayoutBinding) binding).setEntity((ListTestEntity) entity);
        }
    }
}
