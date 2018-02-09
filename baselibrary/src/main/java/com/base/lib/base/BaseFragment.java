package com.base.lib.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.base.lib.R;
import com.base.lib.base.constants.BaseLogConstant;
import com.base.lib.dialog.ConfirmDialog;
import com.base.lib.dialog.EmptyDialog;

/**
 * Created by kec on 2017/8/3.
 */

public class BaseFragment extends Fragment {

    private int loadingColor1;
    private int loadingColor2;
    private int loadingColor3;
    protected int[] loadingColors;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EmptyDialog alert;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoadingColor();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 这里定义了loading的彩色效果
     */
    public void initLoadingColor() {
        loadingColor1 = getResources().getColor(R.color.colorPrimary);
        loadingColor2 = getResources().getColor(R.color.orange);
        loadingColor3 = getResources().getColor(R.color.grey_deep);
        loadingColors = new int[]{loadingColor1, loadingColor2, loadingColor3};
    }

    public void toast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    public void toast(int content) {
        Toast.makeText(getActivity(), String.valueOf(content), Toast.LENGTH_SHORT).show();
    }

    /**
     * 带动画的跳转
     */
    public void startAc(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_open_in, R.anim.activity_open_out);
    }

    /**
     * 带动画的跳转
     */
    public void startAcForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.activity_open_in, R.anim.activity_open_out);
    }

    /**
     * 带动话的结束
     */
    public void finishAc() {
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.activity_close_in, R.anim.activity_close_out);
    }

    public void showRefreshing() {
        if (swipeRefreshLayout != null && !swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
            if (alert == null) {
                alert = new EmptyDialog.Builder(getActivity())// builder
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

    /**
     * 柯聪的框架中接口调用的进度框都是activity的
     */
    public void showProgress() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.showProgress();
        }
//        dialogShow();
    }

    public void dismissProgress() {
//        dialogDismiss();
        if (getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.dismissProgress();
        }
    }

    private ProgressDialog progress;

    synchronized protected void dialogShow() {
        try {

            if (null == getActivity()) {
                return;
            }

            if (null == progress) {
                progress = new ProgressDialog(getActivity());
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
     * 结束弹框
     */
    public void confirmDialog(String message) {
        ConfirmDialog alert = new ConfirmDialog.Builder(getActivity())// builder
                .setContent(message) //TODO 内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface mdialog, int which) {
                        mdialog.dismiss();
                        BaseFragment.this.finishAc();
                    }
                })
                .create();
        alert.show();
    }

    /**
     * 通知弹框
     *
     * @param message
     */
    public void dialog(String message) {
        ConfirmDialog alert = new ConfirmDialog.Builder(getActivity())// builder
                .setContent(message) //TODO 内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface mdialog, int which) {
                        mdialog.dismiss();
                    }
                })
                .create();
        alert.show();
    }

    public void dialog(String message, boolean gravity) {
        ConfirmDialog alert = new ConfirmDialog.Builder(getActivity())// builder
                .setContent(message) //TODO 内容
                .setContentGravity(gravity)//内容居左显示
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface mdialog, int which) {
                        mdialog.dismiss();
                    }
                })
                .create();
        alert.show();
    }


    public void confirmDialog(String message, DialogInterface.OnClickListener clickListener) {
        ConfirmDialog alert = new ConfirmDialog.Builder(getActivity())// builder
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

    public void finishCurrent() {

//        if (getActivity().getSupportFragmentManager().getBackStackEntryCount()>1) {
//
//        }
    }


    public BaseActivity obtainActivity() {
        return (BaseActivity) getActivity();
    }

    public void handleError(Throwable throwable) {
        Log.d(BaseLogConstant.tag, throwable.getMessage());
    }

}
