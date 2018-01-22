package com.base.lib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;


/**
 */
public class ChannelTool {
    private static String CHANNEL = "";


    private static final String DEFAULT_CHANNEL = "MarketNotSet";
    private static final String ERROR_CHANNEL = "MarketReadError";


    public static String getAppMarketName(Context context) {
        if (Tool.isBlank(CHANNEL)) {
            CHANNEL = getChannelFromMetaData(context);
        }
        return CHANNEL;
    }

    private static String getChannelFromMetaData(Context context) {
        String channel = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            channel = ERROR_CHANNEL;
        }
        if (Tool.isBlank(channel)) {
            channel = DEFAULT_CHANNEL;
        }
        return channel;
    }
}
