package com.base.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 */

public abstract class CacheUtil {

    private static final HashMap<String, WeakReference<SharedPreferences>> cacheMap = new HashMap<>();

    SharedPreferences preferences;

    public CacheUtil(Context context, String fileKey) {
        if (cacheMap.get(fileKey) == null || cacheMap.get(fileKey).get() == null) {
            preferences = context.getApplicationContext().getSharedPreferences(fileKey, Context.MODE_PRIVATE);
            WeakReference<SharedPreferences> weak = new WeakReference<>(preferences);
            cacheMap.put(fileKey, weak);
        } else {
            preferences = cacheMap.get(fileKey).get();
        }
    }
}
