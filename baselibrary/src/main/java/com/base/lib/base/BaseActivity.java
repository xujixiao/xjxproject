package com.base.lib.base;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.base.lib.base.constants.BaseLogConstant;
import com.base.lib.manager.ActivityManager;

/**
 * Created by xujixiao on 2017/8/3.
 */

public class BaseActivity extends AppCompatActivity {

    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //记录每一个Activity
        ActivityManager.getInstance().pushOneActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().popOneActivity(this);
        super.onDestroy();
    }

    public void finishCurrent() {
        finish();
    }

    public void showProgress() {
        dialogShow();
    }

    public void dismissProgress() {
        dialogDismiss();
    }


    private ProgressDialog progress;

    synchronized protected void dialogShow() {
        try {
            if (null == progress) {
                progress = new ProgressDialog(this);
                progress.setMessage("请稍候……");
                progress.setIndeterminate(true);
                progress.setCancelable(false);
                progress.setCanceledOnTouchOutside(false);
            }
            if (null != progress && !progress.isShowing()) {
                progress.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    synchronized protected void dialogDismiss() {
        try {
            if (null != progress && progress.isShowing()) {
                progress.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public BaseActivity obtainActivity() {
        return this;
    }

    public void handleError(Throwable throwable) {
        Log.d(BaseLogConstant.tag, throwable.getMessage());
    }
}
