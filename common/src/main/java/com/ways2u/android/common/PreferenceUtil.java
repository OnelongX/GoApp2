package com.ways2u.android.common;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 偏好设置工具
 * 
 */
public class PreferenceUtil {
	private static SharedPreferences setting;
	private static SharedPreferences.Editor pen;
	
	/**
	 * 把字符串保存到偏好设置中
	 * 
	 * @param context
	 *            上下文环
	 * @param key
	 *
	 * @param value
	 *
	 */
	public static void setStringValue(Context context, String key, String value) {
		// 只被本应用使
		setting = context.getSharedPreferences("repair_user", Context.MODE_PRIVATE);
		pen = setting.edit();
		pen.putString(key, value);
		pen.commit();
	}
	public static void clearAllPrefer(){
		if(pen!=null){
			pen.clear();
		}
	}

	/**
	 * 根据键获取
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getStringValue(Context context, String key) {
		// 只被本应用使
		setting = context.getSharedPreferences("repair_user", Context.MODE_PRIVATE);
		return setting.getString(key, "1");
	}
	
	public static void setIntValue(Context context, String key, int value) {
		// 只被本应用使
		setting = context.getSharedPreferences("repair_user", Context.MODE_PRIVATE);
		pen = setting.edit();
		pen.putInt(key, value);
		pen.commit();
	}

	/**
	 * 根据键获取
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static int getIntValue(Context context, String key) {
		// 只被本应用使
		setting = context.getSharedPreferences("repair_user", Context.MODE_PRIVATE);
		return setting.getInt(key, 0);
	}

	/**
	 * 把boolean保存到偏好设置中
	 * 
	 * @param context
	 *            上下文环
	 * @param key
	 *
	 * @param value
	 *
	 */
	public static void setBooleanValue(Context context, String key,
			boolean value) {
		// 只被本应用使
		setting = context.getSharedPreferences("repair_user", Context.MODE_PRIVATE);
		pen = setting.edit();
		pen.putBoolean(key, value);
		pen.commit();
	}

	/**
	 * 根据键获取
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(Context context, String key) {
		// 只被本应用使
		setting = context.getSharedPreferences("repair_user", Context.MODE_PRIVATE);
		return setting.getBoolean(key, false);
	}
	
	
}
