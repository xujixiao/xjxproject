package com.base.lib.tools;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastInstance {
    private static int Y_OFFSET = 0;
    private static ToastInstance instance = null;
    private Toast shortToast, longToast;

    private ToastInstance() {
    }

    public static ToastInstance getInstance() {
        if (null == instance)
            instance = new ToastInstance();
        return instance;
    }

    public void showShortToast(Context context, Object object) {
        String content = String.valueOf(object);
        if (object instanceof java.lang.Integer && null != content
                && content.trim().length() != 0) {
            content = context.getResources().getString(
                    Integer.parseInt(content));
        }
        if (null == shortToast) {
            shortToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            int xOffset = shortToast.getXOffset();
            int yOffset = shortToast.getYOffset() + Y_OFFSET;
            shortToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                    xOffset, yOffset);
        } else {
            shortToast.setText(content);
        }
        shortToast.show();
    }

    public void showLongToast(Context context, Object object) {
        String content = String.valueOf(object);
        if (object instanceof java.lang.Integer && null != content
                && content.trim().length() != 0) {
            content = context.getResources().getString(
                    Integer.parseInt(content));
        }
        if (null == longToast) {
            longToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            int xOffset = longToast.getXOffset();
            int yOffset = longToast.getYOffset() + Y_OFFSET;
            longToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                    xOffset, yOffset);
        } else {
            longToast.setText(content);
        }
        longToast.show();
    }
}
