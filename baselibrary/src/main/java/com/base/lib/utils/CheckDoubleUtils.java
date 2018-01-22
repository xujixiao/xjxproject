package com.base.lib.utils;

/*****************************************************
 *
 * time: 2017/7/31 10:32
 * name: com.wzdai.baselibrary.utils
 * email: xujixiao@wzdai.com
 * modifytime:
 * description:检查是否是快速点击
 * ***************************************************
 */
public class CheckDoubleUtils {
    //private static long lastClickTime;

    private static long lastClickTime = 0L; //上一次点击的时间

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isShortDoubleClick(int overTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < overTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isFastClick(long ClickIntervalTime) {
        long ClickingTime = System.currentTimeMillis();
        if (ClickingTime - lastClickTime < ClickIntervalTime) {
            return true;
        }
        lastClickTime = ClickingTime;
        return false;
    }

}