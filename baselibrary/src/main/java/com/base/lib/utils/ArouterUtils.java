package com.base.lib.utils;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.lib.base.constants.LogConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.utils.ArouterUtils.java
 *
 * Email: xujixiao@wzdai.com
 * Date: 2017/11/30 16:27
 * Description:
 * History:
 *
 * xujixiao      16:27    1.0        Create
 */
public class ArouterUtils {

    // 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
//    @Route(path = "/test/activity")
//    public class YourActivity extend Activity {
//    ...
//    }
    public static void inject(Activity activity) {
        ARouter.getInstance().inject(activity);
    }

    public static void inject(Fragment fragment) {
        ARouter.getInstance().inject(fragment);
    }

    /**
     * 应用内简单的跳转(通过URL跳转在'进阶用法'中)
     * 构建标准的路由请求
     *
     * @param path
     */
    public static void go(String path) {
        ARouter.getInstance().build(path).navigation();
    }


    public static void goWithAnim(String path, int enterAnim, int outAnim,Activity activity) {
        ARouter.getInstance().build(path).withTransition(enterAnim, outAnim).navigation(activity);
    }

    /**
     * 不建议使用
     *
     * @param path
     * @param groupName
     */
    public static void goWithGroupName(String path, String groupName) {
        ARouter.getInstance().build(path, groupName).navigation();
    }


    public static void goWithUri(Uri uri) {
        ARouter.getInstance().build(uri).navigation();
    }

    /**
     * 构建标准的路由请求，startActivityForResult
     * navigation的第一个参数必须是Activity，第二个参数则是RequestCode
     *
     * @param path
     * @param activity
     * @param requestCode
     */
    public static void goNeedReturnData(String path, Activity activity, int requestCode) {
        ARouter.getInstance().build(path).navigation(activity, requestCode);
    }

    /**
     * 直接传递Bundle
     */
    public static void goWithBundle(String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    public static void go(String path, Activity activity) {
        ARouter.getInstance().build(path).navigation(activity, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                Log.d(LogConstants.AROUTER, postcard.toString());
            }

            @Override
            public void onFound(Postcard postcard) {
                super.onFound(postcard);
                Log.d(LogConstants.AROUTER, postcard.toString());
            }

            @Override
            public void onLost(Postcard postcard) {
                super.onLost(postcard);
                Log.d(LogConstants.AROUTER, postcard.toString());

            }

            @Override
            public void onInterrupt(Postcard postcard) {
                super.onInterrupt(postcard);
                Log.d(LogConstants.AROUTER, postcard.toString());
            }
        });
    }

    /**
     * 绿色通道跳转
     *
     * @param path
     */
    public static void goWithGreenChannel(String path) {
        ARouter.getInstance().build(path).greenChannel().navigation();
    }

    /**
     * 跳转并携带参数
     *
     * @param path
     * @param params
     */
    public static void goWithArguments(String path, HashMap<String, Object> params) {
        goWithArguments(path, params, null);
    }

    public static void goWithAnimation(String path, int enterId, int outId) {
        ARouter.getInstance().build(path).withTransition(enterId, outId).navigation();
    }

    /**
     * 跳转并携带参数
     *
     * @param path
     * @param params
     * @param activity
     */
    public static void goWithArguments(String path, HashMap<String, Object> params, Activity activity) {
        Postcard postcard = ARouter.getInstance().build(path);
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    if (entry.getValue() instanceof String) {
                        postcard.withString(entry.getKey(), (String) entry.getValue());
                    } else if (entry.getValue() instanceof Integer) {
                        postcard.withInt(entry.getKey(), (int) entry.getValue());
                    } else if (entry.getValue() instanceof Long) {
                        postcard.withLong(entry.getKey(), (Long) entry.getValue());
                    } else if (entry.getValue() instanceof Object) {
                        postcard.withObject(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        if (activity == null) {
            postcard.navigation();
        } else {
            postcard.navigation(activity, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                }

                @Override
                public void onFound(Postcard postcard) {
                    super.onFound(postcard);
                }

                @Override
                public void onLost(Postcard postcard) {
                    super.onLost(postcard);
                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    super.onInterrupt(postcard);
                }
            });
        }
    }


}
