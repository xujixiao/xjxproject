package com.base.lib.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class AppUtils {

    /**
     * 获取设备IMEI号
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {

        String imei = "";
        try {
            // 获取设备信息
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (null != tm) {
                imei = tm.getDeviceId();
            }
            if (null == imei) {
                imei = "";
            }

            // getLine1Number 手机号
            // getSimSerialNumber SIM卡序号
            // getSubscriberId IMSI

        } catch (SecurityException e) {
            e.printStackTrace();
//            ErrorUtils.uploadException(context, "getIMEI->获取设备信息出错，缺少权限.", e);
        } catch (Exception e) {
            e.printStackTrace();
//            ErrorUtils.uploadException(context, "获取设备信息出错.", e);
        }
        return imei;
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (null != packageManager) {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                if (null != packageInfo) {
                    int labelRes = packageInfo.applicationInfo.labelRes;
                    return context.getResources().getString(labelRes);
                }
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
//            ErrorUtils.uploadException(context, "AppUtils.getAppName", e);
        }
        return "Lincoln";
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (null != packageManager) {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                if (null != packageInfo) {
                    return packageInfo.versionName;
                }
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
//            ErrorUtils.uploadException(context, "AppUtils.getVersionName", e);
        }
        return null;
    }

    /**
     * 清除sp
     *
     * @param context
     */
    public static void clearSharedPreferences(Context context) {
        PreferenceUtils.clear(context);
    }


    /**
     * 清除应用缓存
     *
     * @param context
     */
    public static void clearCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * 清空SD卡中当前应用建立的文件夹 <br>
     * 创建时间：2015-7-23 下午3:56:06
     */
    public static void clearSDFile() {
        // 删除数据
        delAllFile(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "lincoln");

    }


    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return <br>
     * 创建时间：2015-7-23 下午3:53:06
     */
    public static boolean delAllFile(String path) {

        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (!file.isDirectory()) {
            return false;
        }

        boolean flag = false;
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹完整绝对路径 <br>
     *                   创建时间：2015-7-23 下午3:52:48
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 去掉数组中重复的元素
     * @param arr
     * @return
     */
    public static Object[] unique(Object[] arr){
        //实例化一个set集合
        Set set = new HashSet();
        //遍历数组并存入集合,如果元素已存在则不会重复存入
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        //返回Set集合的数组形式
        return set.toArray();
    }

}
