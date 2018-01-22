package com.base.lib.tools;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class PreferenceUtils {
	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "ancient_dimension_data";

	public static String getPrefString(Context context, String key,
                                       final String defaultValue) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			return preferences.getString(key, defaultValue);
		}else {
			return defaultValue;
		}
	}

	public static void setPrefString(Context context, final String key,
                                     final String value) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			final SharedPreferences.Editor editor = preferences.edit();
			editor.putString(key, value);
			SharedPreferencesCompat.apply(editor);
		}
	}

	public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			return preferences.getBoolean(key, defaultValue);
		}else{
			return defaultValue;
		}
	}

	public static void setPrefBoolean(Context context, final String key,
                                      final boolean value) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			final SharedPreferences.Editor editor = preferences.edit();
			editor.putBoolean(key, value);
			SharedPreferencesCompat.apply(editor);
		}
	}

	public static int getPrefInt(Context context, final String key,
                                 final int defaultValue) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			return preferences.getInt(key, defaultValue);
		}else {
			return defaultValue;
		}
	}

	public static void setPrefInt(Context context, final String key,
                                  final int value) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			final SharedPreferences.Editor editor = preferences.edit();
			editor.putInt(key, value);
			SharedPreferencesCompat.apply(editor);
		}
	}

	public static float getPrefFloat(Context context, final String key,
                                     final float defaultValue) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			return preferences.getFloat(key, defaultValue);
		}else {
			return defaultValue;
		}
	}

	public static void setPrefFloat(Context context, final String key,
                                    final float value) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			final SharedPreferences.Editor editor = preferences.edit();
			editor.putFloat(key, value);
			SharedPreferencesCompat.apply(editor);
		}
	}

	public static long getPrefLong(Context context, final String key,
                                   final long defaultValue) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			return preferences.getLong(key, defaultValue);
		}else {
			return defaultValue;
		}
	}

	public static void setPrefLong(Context context, final String key,
                                   final long value) {
		if(null != context){
			final SharedPreferences preferences = context.getSharedPreferences(
					FILE_NAME, Context.MODE_PRIVATE);
			final SharedPreferences.Editor editor = preferences.edit();
			editor.putLong(key, value);
			SharedPreferencesCompat.apply(editor);
		}
	}

	/**
	 * 移除某个key值已经对应的值
	 * 
	 * @param context
	 * @param key
	 */
	public static void remove(Context context, String key) {
		if(null != context){
			SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,
					Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			editor.remove(key);
			SharedPreferencesCompat.apply(editor);
		}
	}

	/**
	 * 清除所有数据
	 * 
	 * @param context
	 */
	public static void clear(Context context) {
		if(null != context){
			SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,
					Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			editor.clear();
			SharedPreferencesCompat.apply(editor);
		}
	}

	/**
	 * 查询某个key是否已经存在
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean contains(Context context, String key) {
		if(null != context){
			SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,
					Context.MODE_PRIVATE);
			return preferences.contains(key);
		}else {
			return false;
		}
	}

	/**
	 * 返回所有的键值对
	 * 
	 * @param context
	 * @return
	 */
	public static Map<String, ?> getAll(Context context) {
		if(null != context){
			SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,
					Context.MODE_PRIVATE);
			return preferences.getAll();
		}else {
			return null;
		}
	}

	/**
	 * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
	 * 
	 * @author zhy
	 * 
	 */
	private static class SharedPreferencesCompat {
		private static final Method sApplyMethod = findApplyMethod();

		/**
		 * 反射查找apply的方法
		 * 
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static Method findApplyMethod() {
			try {
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e) {
			}

			return null;
		}

		/**
		 * 如果找到则使用apply执行，否则使用commit
		 * 
		 * @param editor
		 */
		public static void apply(SharedPreferences.Editor editor) {
			try {
				if (sApplyMethod != null) {
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
			editor.commit();
		}
	}

}