package com.base.lib.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.widget.Toast;

import com.base.lib.R;
import com.base.lib.dialog.ConfirmDialog;
import com.base.lib.dialog.EmptyDialog;
import com.base.lib.manager.MyActivityManager;
import com.base.lib.tools.PreferenceUtils;
import com.base.lib.tools.ToastUtils;
import com.base.lib.utils.LogUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by kec on 2017/8/3.
 */

public class BaseActivity extends RxAppCompatActivity {


    private SwipeRefreshLayout swipeRefreshLayout;
    private EmptyDialog alert;
    private int loadingColor1;
    private int loadingColor2;
    private int loadingColor3;
    protected int[] loadingColors;
    protected FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //记录每一个Activity
        MyActivityManager.getInstance().pushOneActivity(this);

        initLoadingColor();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void toast(int content) {
        Toast.makeText(this, String.valueOf(content), Toast.LENGTH_SHORT).show();
    }

    /**
     * 带动画的跳转
     */
    public void startAc(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open_in, R.anim.activity_open_out);
    }

    public void startAc1(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
    }

    /**
     * 带动画的跳转
     */
    public void startAcForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_open_in, R.anim.activity_open_out);
    }

    /**
     * 带动话的结束
     */
    public void finishAc() {
        finish();
        overridePendingTransition(R.anim.activity_close_in, R.anim.activity_close_out);
    }


    /**
     * 这里定义了loading的彩色效果
     */
    private void initLoadingColor() {
        loadingColor1 = getResources().getColor(R.color.colorPrimary);
        loadingColor2 = getResources().getColor(R.color.orange);
        loadingColor3 = getResources().getColor(R.color.grey_deep);
        loadingColors = new int[]{loadingColor1, loadingColor2, loadingColor3};
    }


    public void setLoading(SwipeRefreshLayout layout) {
        swipeRefreshLayout = layout;
    }

    public void showRefreshing() {
        if (swipeRefreshLayout != null && !swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
            if (alert == null) {
                alert = new EmptyDialog.Builder(this)// builder
                        .create();

            }
            alert.show();
        }
    }

    public void hideRefreshing() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
            if (alert.isShowing()) {
                alert.hide();
            }
        }
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


    /**
     * 验证是否登录
     *
     * @return
     */
    public static boolean verifyIsLogin(Context context) {

        String userId = PreferenceUtils.getPrefString(context, "user_id", "");

        if (TextUtils.isEmpty(userId)) {
            //用户id
            ToastUtils.showShortToast("您尚未登录！");
            return false;
        }

        return true;
    }


    /**
     * 结束弹框
     */
    public void confirmDialog(String message) {
        ConfirmDialog alert = new ConfirmDialog.Builder(this)// builder
                .setContent(message)
                .setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface mdialog, int which) {
                        mdialog.dismiss();
                        BaseActivity.this.finishAc();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alert.show();
    }

    public void confirmDialog(String message, DialogInterface.OnClickListener clickListener) {
        ConfirmDialog alert = new ConfirmDialog.Builder(this)// builder
                .setContent(message)
                .setCancelable(true)
                .setPositiveButton("确定", clickListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alert.show();
    }


    public BaseActivity obtainActivity() {
        return this;
    }

    public void handleError(Throwable throwable) {
        LogUtils.d(throwable.getMessage());
    }
}
