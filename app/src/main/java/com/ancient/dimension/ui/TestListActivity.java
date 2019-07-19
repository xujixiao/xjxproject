package com.ancient.dimension.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.ancient.dimension.R;
import com.ancient.dimension.databinding.TestListItemLayoutBinding;
import com.ancient.dimension.ui.entity.JsonEntity;
import com.ancient.dimension.ui.entity.ListTestEntity;
import com.ancient.dimension.ui.entity.TopicEntity;
import com.ancient.dimension.ui.presenter.MainPresenter;
import com.ancient.dimension.ui.presenter.TestPresenter;
import com.ancient.dimension.ui.presenter.TopicPresenter;
import com.base.lib.base.BaseListActivity;
import com.base.lib.base.adapter.IHolderType;
import com.base.lib.databinding.SmartCommonListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11073 on 2018/2/24.
 */

public class TestListActivity extends BaseListActivity<TopicPresenter, SmartCommonListLayoutBinding>
        implements TopicPresenter.View {

    private List<TopicEntity> mList = new ArrayList();
    private int page = 1;

    public static void start(Context context) {
        Intent starter = new Intent(context, TestListActivity.class);
//    starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected TopicPresenter createPresenter() {
        return new TopicPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleViewEntity.title.set("每日一笑");
        mAdapter.refreshData(mList);
        mvpPresenter.getTopic(this, page, 10);
    }

    @Override
    public void onResult(List<TopicEntity> list) {
        loadDataComplete();
        mList.addAll(list);
        mAdapter.refreshData(mList);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.test_list_item_layout;
    }

    @Override
    public void refreshNetData() {
        page = 1;
        mvpPresenter.getTopic(this, page, 10);
    }

    @Override
    public void loadMoreData() {
        page++;
        mvpPresenter.getTopic(this, page, 10);
    }

    @Override
    public <E extends IHolderType, B extends ViewDataBinding> void bindData(E entity, int position, B binding) {
        if (binding instanceof TestListItemLayoutBinding) {
            ((TestListItemLayoutBinding) binding).setEntity((TopicEntity) entity);
        }
    }
}
