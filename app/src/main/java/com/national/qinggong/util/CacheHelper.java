package com.national.qinggong.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author liuyang
 * @parame getSharedPreferences
 *
 */
public class CacheHelper {
	public static String getAlias(Context context, String packageName) {
		SharedPreferences preferences = getSharedPreferences(context);
		return preferences.getString(packageName, null);
	}

	public static void setAlias(Context context, String packageName,
                                String alias) {
		SharedPreferences preferences = getSharedPreferences(context);
		Editor edit = preferences.edit();
		edit.putString(packageName, alias);
		edit.commit();
	}

	public static void delAlias(Context context, String packageName) {
		SharedPreferences preferences = getSharedPreferences(context);
		Editor edit = preferences.edit();
		edit.remove(packageName);
		edit.commit();
	}

	private static SharedPreferences getSharedPreferences(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				"cache_merchant", Context.MODE_PRIVATE);
		return preferences;
	}

	// 存多种类型数据

	public static void put(Context context, String key, Object object) {
		SharedPreferences preferences = getSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}

		editor.commit();
	}

	public static Object get(Context context, String key, Object defaultObject) {
		SharedPreferences preferences = getSharedPreferences(context);
		if (defaultObject instanceof String) {
			return preferences.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return preferences.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return preferences.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return preferences.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return preferences.getLong(key, (Long) defaultObject);
		}
		return null;
	}

	/**
	 * 移除某个key值已经对应的值
	 *
	 * @param context
	 * @param key
	 */
	public static void remove(Context context, String key) {
		SharedPreferences preferences = getSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(key);
		editor.commit();
	}

	/**
	 * 清除所有数据
	 *
	 * @param context
	 */
	public static void clear(Context context) {
		SharedPreferences preferences = getSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 查询某个key是否已经存在
	 *
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean contains(Context context, String key) {
		SharedPreferences preferences = getSharedPreferences(context);
		return preferences.contains(key);
	}

}