package com.base.lib.base.bind;

import android.databinding.BindingAdapter;
import android.widget.ImageView;


/**
 * Copyright (C), 2011-2017
 * FileName: com.base.lib.base.bind.ImageViewBind.java
 * <p>
 * Date: 2017/12/25 10:12
 * Description:
 * History:
 * <p>
 * xujixiao      10:12    1.0        Create
 */
public class ImageViewBind {
    @BindingAdapter("android:imageId")
    public static void bindImageId(ImageView imageView, int id) {
        imageView.setImageResource(id);
    }

    @BindingAdapter("android:image")
    public static void bindImage(ImageView imageView, Object imageSrc) {
        if (imageSrc instanceof Integer) {
            imageView.setImageResource((int) imageSrc);
        } else if (imageSrc instanceof String) {
//            Glide.with(imageView.getContext()).load(imageSrc).into(imageView);
        }
    }
}
