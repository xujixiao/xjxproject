package com.ancient.dimension.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ancient.dimension.R;
import com.ancient.dimension.TagConstants;
import com.ancient.dimension.databinding.ActMainBinding;
import com.ancient.dimension.ui.dialog.DownloadDialog;
import com.ancient.dimension.ui.entity.JsonEntity;
import com.ancient.dimension.ui.entity.Person;
import com.ancient.dimension.ui.presenter.MainPresenter;
import com.ancient.dimension.ui.presenter.TestPresenter;
import com.base.lib.base.SActivity;
import com.base.lib.tools.ToastUtils;
import com.tencent.smtt.utils.LogFileUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;


public class MainActivity extends SActivity<MainPresenter, ActMainBinding> implements MainPresenter.view {

    private DownloadDialog mDownloadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding.tvBuglyTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TestListActivity.start(MainActivity.this);
//                mvpPresenter.gianInfo(MainActivity.this);
                mvpPresenter.getTopic(MainActivity.this);
            }
        });
        mDownloadDialog = new DownloadDialog(this);
//        mDownloadDialog.show();
//        mDownloadDialog.setProgressBar(89);
        Bmob.initialize(this, "f7f57911c84f75825b71a291b784f9f6");
//        Person p2 = new Person();
//        p2.setName("lucky");
//        p2.setAddress("北京海淀");
//        p2.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if (e == null) {
//                    ToastUtils.showShortToast("添加数据成功，返回objectId为：" + objectId);
//                } else {
//                    ToastUtils.showShortToast("创建数据失败：" + e.getMessage());
//                }
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onJsonResult(JsonEntity jsonEntity) {
//        判断逻辑：
//        首先判断is_update:
//        true -> 强更，下载update_url中的apk，执行强更，安装apk
//        false -> 不强更，判断is_wap, 如果is_wap = true，那么跳转wap_url，如果is_wap = false，不跳转，进入壳的内容
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("899cb616c4", new QueryListener<Person>() {
            @Override
            public void done(Person object, BmobException e) {
                if (e == null) {
                    if (object.getAddress().equals("北京海淀")) {
                        ToastUtils.showShortToast("查询成功");
                        handleJsonEntity(jsonEntity);
                    }
                } else {
                    ToastUtils.showShortToast("查询失败：" + e.getMessage());
                }
            }
        });


    }

    private void handleJsonEntity(JsonEntity jsonEntity) {
        if (jsonEntity.is_update.equals("0")) {
            if (jsonEntity.is_wap.equals("0")) {
                // TODO: 2019/7/18 壳工程
                mvpPresenter.downloadFile(this, jsonEntity.update_url);
            } else {
                WebActivity.start(this, jsonEntity.wap_url);
            }
        } else if (!jsonEntity.is_update.equals("0")) {
            /*强制更新*/
            mvpPresenter.downloadFile(this, jsonEntity.update_url);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void onProgress(int progress) {
        Log.d(TagConstants.TAG, "毁掉进程" + progress);
        if (progress > 0) {
            if (!mDownloadDialog.isShowing()) {
                mDownloadDialog.show();
            }
            mDownloadDialog.setProgressBar(progress);
        }
    }

    @Override
    public void onDownloadCompelte() {
        mDownloadDialog.dismiss();
    }
}
