package com.base.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.base.lib.tools.PreferenceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppUtil {


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        /*xujixixoa*/
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    // 获取当前设备ID
    public static String getDeviceId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 获取当前设备信息
    public static String getDeviceName() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 获取当前系统的版本号
    public static String getOsVersion() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void installApk(Context context, String path) {
        if (!TextUtils.isEmpty(path) && (path).contains(".apk")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            File file = new File(path);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法: getVersionCode
     * 描述: 获取客户端版本号
     *
     * @return int    版本号
     */
    public static int getVersionCode(Context context) {
        int versionCode;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            versionCode = 999;
        }
        return versionCode;
    }

    /**
     * 方法: getVersion
     * 描述: 获取客户端版本号
     *
     * @return int    版本号
     */
    public static String getVersion(Context context) {
        String versionCode;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            versionCode = "0.0.0";
        }
        return versionCode;
    }


    public static boolean isFlyme() {
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是魅族操作系统
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/6/18,9:43
     * <h3>UpdateTime</h3> 2016/6/18,9:43
     * <h3>CreateAuthor</h3> vera
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     *
     * @return true 为魅族系统 否则不是
     */
    public static boolean isMeizuFlymeOS() {
        /*判断是否是oppo的手机需要执行腾讯的解析*/
        if ("OPPO R9s Plus".equals(Build.MODEL)) {
            return true;
        }
        /* 获取魅族系统操作版本标识*/
        String meizuFlymeOSFlag = getSystemProperty("ro.build.display.id", "");
        if (TextUtils.isEmpty(meizuFlymeOSFlag)) {
            return false;
        } else if (meizuFlymeOSFlag.contains("flyme") || meizuFlymeOSFlag.toLowerCase().contains("flyme")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取系统属性
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/6/18,9:35
     * <h3>UpdateTime</h3> 2016/6/18,9:35
     * <h3>CreateAuthor</h3> vera
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     *
     * @param key          ro.build.display.id
     * @param defaultValue 默认值
     * @return 系统操作版本标识
     */
    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
    }


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
        } catch (PackageManager.NameNotFoundException e) {
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
        } catch (PackageManager.NameNotFoundException e) {
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
     *
     * @param arr
     * @return
     */
    public static Object[] unique(Object[] arr) {
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

