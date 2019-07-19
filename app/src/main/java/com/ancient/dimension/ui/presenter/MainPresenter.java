package com.ancient.dimension.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;

import com.ancient.dimension.TagConstants;
import com.ancient.dimension.ui.entity.JsonEntity;
import com.base.lib.base.BasePresenter;
import com.base.lib.base.view.IView;
import com.base.lib.tools.ToastUtils;
import com.google.gson.Gson;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxFileCallBack;
import com.tamic.novate.callback.RxStringCallback;

import java.io.File;
import java.util.HashMap;

/**
 * Created by 11073 on 2018/1/30.
 */

public class MainPresenter extends BasePresenter<MainPresenter.view> {

    public interface view extends IView {
        void onJsonResult(JsonEntity jsonEntity);

        void onProgress(int progress);

        void onDownloadComplete();
    }


    public void gainInfo(Context context) {
//        http://www.ds06ji.com:15780/back/api.php?app_id=122000055
        new Novate.Builder(context)
                .baseUrl("http://www.ds06ji.com:15780/")
                .build()
                .rxGet("back/api.php?app_id=122000055", new HashMap<>(), new RxStringCallback() {
                    @Override
                    public void onNext(Object tag, String response) {
                        byte[] str = Base64.decode(response, Base64.DEFAULT);
                        JsonEntity jsonEntity = new Gson().fromJson(new String(str), JsonEntity.class);
                        mvpView.onJsonResult(jsonEntity);
                    }

                    @Override
                    public void onError(Object tag, Throwable e) {
                        Log.d(TagConstants.TAG, e.toString());
                    }

                    @Override
                    public void onCancel(Object tag, Throwable e) {

                    }
                });

    }


    public void downloadFile(Context context, String url) {
        Log.d(TagConstants.TAG, "开始执行下载程序");
        String path = Environment.getExternalStorageDirectory() + "/my/";

        new Novate.Builder(context)
                .baseUrl("https://down.58yskkk.com/")
                .build()
                .rxGet(url, new HashMap<String, Object>(), new RxFileCallBack(path, "temp.apk") {
                    @Override
                    public void onNext(Object tag, File file) {
                        Log.d("xujixiao", file.getAbsolutePath() + "--------------" + file.getAbsoluteFile().getName());
                        Log.d(TagConstants.TAG, file.getName());
                        mvpView.onDownloadComplete();
                        install(context, file.getAbsoluteFile().getPath());
                    }

                    @Override
                    public void onProgress(Object tag, float progress, long downloaded, long total) {
                        Log.d("xujixiao", "开始下载" + progress + "当前时间" + System.currentTimeMillis());
                        if (progress % 3 == 0) {
                            mvpView.onProgress((int) progress);
                        }
                    }

                    @Override
                    public void onError(Object tag, Throwable e) {
                        Log.d("xujixiao", e.getMessage().toString());
                        ToastUtils.showShortToast("下载失败");
                    }

                    @Override
                    public void onCancel(Object tag, Throwable e) {

                    }
                });
    }


    private void install(Context mContext, String filePath) {
        Log.i(TagConstants.TAG, "开始执行安装: " + filePath);
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w(TagConstants.TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    mContext
                    , "com.dongzhen.yings.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Log.w(TagConstants.TAG, "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }
}
