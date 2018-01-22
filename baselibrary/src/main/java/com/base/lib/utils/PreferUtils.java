package com.base.lib.utils;

import com.base.lib.BaseApplication;
import com.base.lib.tools.PreferenceUtils;

/**
 * Created by 11073 on 2018/1/10.
 */

public class PreferUtils {
    public static void setString(String key, String value) {
        PreferenceUtils.setPrefString(BaseApplication.context, key, value);
    }

    public static String getString(String key) {
        return PreferenceUtils.getPrefString(BaseApplication.context, key, "");
    }
}
