package com.base.lib.custom_view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Copyright (C), 2011-2017
 * FileName: com.wzdai.fin.widget.CustomTextView.java
 * Author: xujixiao
 * Date: 2017/12/18 16:25
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      16:25    1.0        Create
 */
public class FontTextView extends AppCompatTextView {
    private Context mContext;

    public FontTextView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    static String fongUrl = "fonts/custom_font.ttf";
    static Typeface tf;

    /***
     * 设置字体
     *
     * @return
     */
    public static Typeface setFont(Context context) {
        if (tf == null) {
            tf = Typeface.createFromAsset(context.getAssets(), fongUrl);
        }
        return tf;
    }

    private void init() {
        setFont(mContext);
        setTypeface(tf);
    }
}
