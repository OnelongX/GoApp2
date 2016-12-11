package com.ways2u.android.net.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.util.Map;

/**
 * Created by huanglong on 2016/12/5.
 */

public class ParamsUtil {

    /**
     *
     * @param context base context
     * @return
     */
    public static Map<String,String> getParams(Context context){
        Map<String,String> params = new ArrayMap<>();
        params.put("p", "android");
        params.put("v", getVersionName(context));
        /*
        params.put("token",
                PreferenceUtil.getStringValue(AfterSaleActivity.this, "token"));
                */
        return params;
    }

    /**
     * @param context
     * @return
     */
    private static String getVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getApplicationContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
