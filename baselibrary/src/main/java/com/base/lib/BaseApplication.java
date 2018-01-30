package com.base.lib;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.bugly.Bugly;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

public class BaseApplication extends Application {

    //获取到主线程的上下文
    public static Context context;
    public final static String BUGLY_APP_ID = "bugly_app_id";
    public final static String BUGLY_APP_SECRET = "bugly_app_secret";
    public final static String BUGLY_DEBUG = "bugly_debug";
    public final static String AROUTE_DEBUG = "aroute_debug";
    protected HashMap<String, String> paramsMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initParamsMap();
        initArouter();
        initX5WebView();
        initBugly();
    }

    private void initBugly() {
        Bugly.init(getApplicationContext(), paramsMap.get(BUGLY_APP_ID), Boolean.valueOf(paramsMap.get(BUGLY_DEBUG)));
        Bugly.setIsDevelopmentDevice(this, Boolean.valueOf(paramsMap.get(BUGLY_DEBUG)));
    }

    protected void initParamsMap() {

    }

    /**
     * 初始化阿里的路由框架
     */
    private void initArouter() {
        if (Boolean.valueOf(paramsMap.get(AROUTE_DEBUG))) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

}
