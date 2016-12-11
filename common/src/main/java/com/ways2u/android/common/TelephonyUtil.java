package com.ways2u.android.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;

public class TelephonyUtil {
	private final TelephonyManager tm;
	private Context mContext;

	public TelephonyUtil(Context context) {
		mContext = context;
		tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
	}

	/**
	 * 取出IMEI
	 * */
	public String getIMEI() {
		return tm.getDeviceId();
	}

	/**
	 * 取出MSISDN，很可能为空
	 * */
	public String getPhoneNumber() {
		return tm.getLine1Number();
	}

	/**
	 * 取出ICCID
	 * */
	public String getICCID() {
		return tm.getSimSerialNumber(); // 取出ICCID
	}

	/**
	 * 取出IMSI
	 * */
	public String getIMSI() {
		return tm.getSubscriberId(); // 取出IMSI
	}

	/**
	 * 取出手机型号
	 * */
	public String getModel() {
		return Build.MODEL;
	}

	/**
	 * 取出系统版本
	 * */
	public String getSDK() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * 取出SDK版本 获取当前系统的android版本号 int
	 * currentapiVersion=android.os.Build.VERSION.SDK_INT
	 * */
	public int getSDKVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * 获取当前应用的版本号：
	 * */
	public String getVersionName() {
		// 获取packagemanager的实例
		PackageManager packageManager = mContext.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(mContext
					.getApplicationContext().getPackageName(), 0);
			return packInfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取当前应用的版本号：
	 * */
	public int getVersionCode() {
		// 获取packagemanager的实例
		PackageManager packageManager = mContext.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(mContext
					.getApplicationContext().getPackageName(), 0);
			return packInfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// String imei = tm.getDeviceId(); //取出IMEI
	// String tel = tm.getLine1Number(); //取出MSISDN，很可能为空
	// String imei =tm.getSimSerialNumber(); //取出ICCID
	// String imsi =tm.getSubscriberId(); //取出IMSI
}
