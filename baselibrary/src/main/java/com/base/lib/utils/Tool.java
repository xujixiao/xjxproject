package com.base.lib.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {

    // unicode转中文
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }

        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public static String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * utf-8 转unicode
     *
     * @param str
     * @return String
     */
    public static String toUnicode(String str) {
        char[] arChar = str.toCharArray();
        int iValue = 0;
        String uStr = "";
        for (int i = 0; i < arChar.length; i++) {
            iValue = (int) str.charAt(i);
            char sValue = str.charAt(i);
            if (iValue <= 256) {
                // uStr+="& "+Integer.toHexString(iValue)+";";
                // uStr+="\\"+Integer.toHexString(iValue);
                uStr += sValue;
            } else {
                // uStr+="&#x"+Integer.toHexString(iValue)+";";
                uStr += "\\u" + Integer.toHexString(iValue);
            }
        }
        return uStr;
    }

    // 转32位大写MD5
    public final static String get32MD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            Log.e("Tool", "NoSuchAlgorithmException:" + e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e("Tool", "UnsupportedEncodingException:" + e.toString());
        }
        if (messageDigest != null) {
            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();
            if (byteArray != null) {
                for (int i = 0; i < byteArray.length; i++) {
                    if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                        md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                    else
                        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
            return md5StrBuff.toString();
        }
        return str;
    }

    /**
     * 判断是否是手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (isBlank(mobiles))
            return false;
        Pattern p = Pattern.compile("^1[0-9]{10}$");
        // ^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断是否是邮箱地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) {
            return false;
        }
        // String str =
        // "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        String str = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 电话号码部分加*号
     *
     * @param telephone
     * @return
     */
    public static String changeMobile(String telephone) {
        if (isBlank(telephone)) {
            return "";
        }
        if (!isMobileNO(telephone))
            return telephone;
        return telephone.substring(0, 3) + "****" + telephone.substring(7, telephone.length());
    }

    public static String changeMobileLast(String telephone) {
        if (isBlank(telephone)) {
            return "";
        }
        if (!isMobileNO(telephone))
            return telephone;
        return "****" + telephone.substring(7, telephone.length());
    }

    /**
     * 姓名加*号
     *
     * @param name
     * @return
     */
    public static String changeName(String name) {
        if (isBlank(name)) {
            return "";
        }
//        return "*" + name.substring(1, name.length());
        return name;
    }

    /**
     * 姓名加*号
     *
     * @param name
     * @return
     */
    public static String changeRealName(String name) {
        if (isBlank(name)) {
            return "****";
        }
        return name.substring(0, 1) + "****";
    }

    /**
     * 身份证号加*号
     *
     * @param identity
     * @return
     */
    public static String changeIdentity(String identity) {
        if (isBlank(identity)) {
            return "";
        }
        if (identity.length() != 15 && identity.length() != 18) {
            return "身份证号码异常";
        }
        if (identity.length() == 18) {
            return identity.substring(0, 6) + "********" + identity.substring(identity.length() - 4, identity.length());
        } else {
            return identity.substring(0, 6) + "******" + identity.substring(identity.length() - 3, identity.length());
        }
    }

    public static String changeBankCard(String bankCard) {
        if (isBlank(bankCard)) {
            return "";
        }
        return "**************" + bankCard.substring(bankCard.length() - 4, bankCard.length());
    }

    // 小数进位
    public static double carryAigit(float number) {
        return Math.ceil(number);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 获得通知栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取手机屏幕的宽高
     *
     * @return int[]
     */
    public static int[] getDeviceWindowsHeightWidth(Context context) {
        int[] screen = new int[2];
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screen[0] = outMetrics.widthPixels;
        screen[1] = outMetrics.heightPixels;
        return screen;
    }


    // 判断是否为数字
    public static boolean isNumeric(String str) {
        if (Tool.isBlank(str))
            return false;
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    // 名字为汉字
    public static boolean isWord(String str) {
        if (Tool.isBlank(str) || str.trim().length() < 2) {
            return false;
        }
        return true;
    }

    public static boolean isRegisterUserName(String name) {
        String str = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{1,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**
     * 校验输入的密码格式
     *
     * @param str 被校验的字符串
     * @return
     */
    public static boolean isPassword(String str) {
        String reg = "^[!~@#\\$%^&*()_+-=<>.'a-zA-Z_0-9]{1,}$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static boolean isPasswordValide(String str) {
        return !TextUtils.isEmpty(str) && str.length() >= 6 && str.length() <= 16;
    }

    /**
     * 检查银行卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0 || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    // 微信分享使用
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    // 判断字符串对象为null或者""
    public static boolean isBlank(String str) {
        return (str == null || str.isEmpty() || "null".equals(str));
    }

    /**
     * 字符串大于等于一定长度
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isLonger(ObservableField<String> str, int length) {
        return isLonger(str.get(), length);
    }

    /**
     * 字符串大于等于一定长度
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isLonger(String str, int length) {
        if (!isBlank(str) && str.length() >= length) {
            return true;
        }
        return false;
    }

    public static List<String> toList(JSONArray arr) {
        List<String> str_list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            try {
                str_list.add(arr.getString(i));
            } catch (JSONException e) {
                Log.e("Tool", "Failed To List");
                return str_list;
            }
        }
        return str_list;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getNowTime(String format) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    public static String newString(String string, String defualt) {
        if (isBlank(defualt)) {
            defualt = "";
        }
        return isBlank(string) ? defualt : string;
    }

    // unix时间戳转日期 yyyy-MM-dd-HH-mm:ss:ms
    @SuppressLint("SimpleDateFormat")
    public static String unixTimeToDate(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }


    // unix时间戳转日期 yyyy-MM-dd-HH-mm:ss:ms
    @SuppressLint("SimpleDateFormat")
    public static String unixTimeToDate(long timestampString) {
        Long timestamp = timestampString * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date(timestamp));
        return date;
    }


    public static String timeToDate(long timestampString, String formats) {
        Long timestamp = timestampString;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new java.util.Date(timestamp));
        return date;
    }

    public static String encodePassword(String password) {
        String str_begin = password.substring(0, 3);
        String str_end = password.substring(3);
        String new_password = str_end + str_begin;
        return new_password;
    }

    public static String decodePassword(String password) {
        int len = password.length();
        String str_begin = password.substring(0, len - 3);
        String str_end = password.substring(len - 3);
        String new_password = str_end + str_begin;
        return new_password;
    }

    public static void goToActivity(Activity fromActivity, Class<?> toActivity) {
        Intent intent = new Intent();
        intent.setClass(fromActivity, toActivity);
        fromActivity.startActivity(intent);
    }


    public static boolean isMX() {
        String deviceName = AppUtil.getDeviceName();
        if (!isBlank(deviceName) && (deviceName.startsWith("MX") || deviceName.startsWith("mx"))) {
            return true;
        }
        return false;
    }


    // 判断view是否存在
    public static boolean isVisible(View view) {
        if (view != null && view.getVisibility() == View.VISIBLE) {
            return true;
        } else
            return false;
    }

    // 标题优化
    public static String changeTitle(String title) {
        if (isBlank(title)) {
            return "";
        } else {
            return title.length() > 8 ? title.substring(0, 8) + "..." : title;
        }
    }

    // 四舍五入(保留两位小数)
    public static double changedoublehalf(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 四舍五入(无小数位)
    public static double changedoublenopoint(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 读本地图片防止OOM
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    // OOM出现 解决办法
    public static Bitmap createBitmap(int width, int height, Bitmap.Config config) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            while (bitmap == null) {
                System.gc();
                System.runFinalization();
                bitmap = createBitmap(width, height, config);
            }
        }
        return bitmap;
    }

    public static float getHeightWidthRatio(Resources res, int resID) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        if (BitmapFactory.decodeResource(res, resID, opt) == null) {
        }
        return ((float) opt.outHeight / (float) opt.outWidth);
    }

    // ImageView默认是不进行图片资源的回收的，需要我们自己在activity或者fragment中进行回收
    public static void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null)
            return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public static int getBitmapSize(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else {
            return data.getByteCount();
        }
    }


    /**
     * 重设布局
     *
     * @param view
     * @param targetView
     * @param position
     */
    public static void resetViewPosition(View view, int targetView, int position) {
        RelativeLayout.LayoutParams invest_herosDetailViewParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        invest_herosDetailViewParams.addRule(position, targetView);
        view.setLayoutParams(invest_herosDetailViewParams);
    }


    /**
     * 判断是否是整形字符串
     *
     * @param num
     * @return
     */
    public static boolean isInteger(String num) {
        if (TextUtils.isEmpty(num)) {
            return false;
        }
        try {
            new BigInteger(num);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断是否是数字
     *
     * @param num
     * @return
     */
    public static boolean isNum(String num) {
        Pattern p = Pattern.compile("-?[0-9]+.*[0-9]*");
        Matcher m = p.matcher(num);
        return m.matches();
    }


    /**
     * 获取double
     *
     * @param num
     * @return
     */
    public static double getDouble(String num) {
        if (TextUtils.isEmpty(num)) {
            return 0;
        }
        try {
            return Double.valueOf(num);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 格式化
     *
     * @param num
     * @return
     */
    public static String formatNum(int num) {
        String format = String.valueOf(num);
        if (format.length() == 1) {
            return "0" + format;
        }
        return format;
    }

    public static String str2DicStr(String num) {
        if (TextUtils.isEmpty(num)) {
            return "0.00";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);
        nf.setMinimumFractionDigits(3);
        nf.setGroupingUsed(false);
        num = nf.format(Double.valueOf(num));
        if (!num.contains(".")) {
            return num + ".00";
        } else {
            String startS = num.substring(0, num.indexOf(".") + 1);
            String endS = num.substring(num.indexOf(".") + 1, num.length());
            if (endS.length() > 2) {
                endS = endS.substring(0, 2);
            } else if (endS.length() == 1) {
                endS = endS + "0";
            } else {
                endS = "00";
            }
            return startS + endS;
        }
    }

    public static InputFilter getInputFilter() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (!Tool.isNum(source.toString()) && !source.equals(".")) {
                    return "";
                }

                if (dest.toString().equals("0") && source.toString().equals("0")) {
                    return "";
                }
                if (dest.toString().contains(".") && source.toString().equals(".")) {
                    return "";
                }
                if (dest.toString().length() == 0 && source.toString().equals(".")) {
                    return "0.";
                }

                if (!dest.toString().contains(".") && dest.length() == 7 && !source.equals(".")) {
                    return "";
                }

                if (dest.toString().contains(".") && dest.subSequence(dest.toString().indexOf(".") + 1, dest.toString().length()).length() == 2) {
                    return "";
                }

                return null;
            }
        };
    }


    public static String formatNowSchedule(double nowSchedule) {

        return new DecimalFormat("#0.00").format(Double.valueOf(nowSchedule) * 100) + "%";
    }

    public static String formatDouble(double nowSchedule) {

        return new DecimalFormat("#0.00").format(nowSchedule) + "";
    }

    public static String codeStr(String str) throws Exception {
        String[] strs = str.split("\\?");
        String rtnUrl = strs[0] + "?abc=123";
        if (strs.length == 1) {
            return rtnUrl;
        }
        String paramStrs = strs[1];
        String[] key_values = paramStrs.split("&");
        for (int i = 0; i < key_values.length; i++) {
            String key_value = key_values[i];
            if (null != key_value && !"".equals(key_value)) {
                int fuhaoIndex = key_value.indexOf("=");
                String key = key_value.substring(0, fuhaoIndex);
                String value = key_value.substring(fuhaoIndex + 1, key_value.length());
                if (value.contains("+")) {
                    rtnUrl = rtnUrl + "&" + key + "=" + value;
                } else {
                    String decodeValue = URLDecoder.decode(value, "UTF-8");
                    rtnUrl = rtnUrl + "&" + key + "=" + decodeValue;
                }
            }
        }
        return rtnUrl;
    }


    public static void hideInputMethod(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(View v) {
        if (v == null) return;
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }


    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        return isClientAvailable(context, "com.tencent.qqlite") || isClientAvailable(context, "com.tencent.mobileqq");
    }

    public static boolean isClientAvailable(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                // LogUtils.e("pn = "+pn);
                if (pn.equalsIgnoreCase(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取外部SD卡路径保存文件位置
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/wzdai/";
    }


}
