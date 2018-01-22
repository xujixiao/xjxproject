package com.base.lib.tools;

import java.util.regex.Pattern;

/**
 * Created by kecong on 2017/9/12.
 */

public class CheckParamsUtils {

    /**
     * 校验手机号码格式
     *
     * @param mobile
     * @return
     */
    public static boolean checkMobile(final CharSequence mobile) {
        return Pattern.compile("^1[0|1|2|3|4|5|6|7|8|9]\\d{9}$").matcher(mobile).matches();
    }

    /**
     * 验证用户名  只可是中英文
     *
     * @param mobile
     * @return
     */
    public static boolean checkName(final CharSequence mobile) {
        return Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z]+$").matcher(mobile).matches();
    }

    /**
     * 校验密码格式 (6~18位且不能有汉字或空格)
     *
     * @param password
     * @return
     */
    public static boolean checkPass(CharSequence password) {
        return Pattern.compile("^[^\\u4e00-\\u9fa5^ ]{6,18}$").matcher(password).matches();
    }

    /**
     * 校验邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(CharSequence email) {
        return Pattern.compile(
                "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2," + "4}|[0-9]{1,3})(\\]?)$")
                .matcher(email).matches();
    }

    /**
     * 校验提现金额格式
     *
     * @param money
     * @return
     */
    public static boolean checkAmount(CharSequence money) {
        return Pattern.compile("^([2-9]|[1-9][0-9])\\d*00$").matcher(money).matches();
    }

}
