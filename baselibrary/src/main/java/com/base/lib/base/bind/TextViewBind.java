package com.base.lib.base.bind;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.base.lib.R;

import static java.lang.Double.parseDouble;


public class TextViewBind {
    @BindingAdapter("android:rightImg")
    public static void setRightImg(TextView textView, int drawableId) {
        Context context = textView.getContext();
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    @BindingAdapter("android:leftImage")
    public static void setLeftImg(TextView textView, int drawableId) {
        Context context = textView.getContext();
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    @BindingAdapter("android:amount")
    public static void setAmount(TextView textView, String amount) {
        try {
            textView.setText((parseDouble(amount) / 10000) + "万");
        } catch (Exception e) {
            textView.setText(amount);
        }
    }

    @BindingAdapter("android:textColorSelect")
    public static void textColorSelect(TextView textView, boolean select) {
        if (select) {
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.red_dd302f));
        } else {
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.black_333333));
        }
    }

}
