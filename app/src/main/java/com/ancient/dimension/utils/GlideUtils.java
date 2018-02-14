package com.ancient.dimension.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.ancient.dimension.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by 11073 on 2018/2/9.
 */

public class GlideUtils {
    public static void loadImage(Fragment fragment, String url, ImageView imageView) {
        Glide.with(fragment)
                .load(url)
                .into(imageView);
    }

    public static void loadImage(Activity activity, String url, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    public interface CallBack {
        void callBackResource(Drawable drawable);
    }

    public static void loadBitmap(Context context, String url, CallBack callBack) {
        Glide.with(context)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (callBack != null) {
                            callBack.callBackResource(resource);
                        }
                    }
                });
    }

    public interface CallBackBitmap {
        void callBackBitmap(Bitmap bitmap);
    }

    public static void loadBitmap(Context context, String url, CallBackBitmap callBackBitmap) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(new CustomTarget() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(resource, transition);
                        if (callBackBitmap != null) {
                            callBackBitmap.callBackBitmap(resource);
                        }
                    }
                });
    }


}
