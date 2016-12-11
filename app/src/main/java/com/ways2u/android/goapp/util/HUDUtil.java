package com.ways2u.android.goapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.ways2u.android.goapp.dagger.ui.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class HUDUtil {
    private Context mContext;
    private Resources mResources;

    @Inject
    public HUDUtil(Activity context){
        this.mContext = context;
        this.mResources = context.getResources();
    }
    
    public void toastShort(String msg) {
        showToast(mContext, msg, Toast.LENGTH_SHORT);  
    }  
  
    public void toastShort(@StringRes int strRes) {
        showToast(mContext, mResources.getString(strRes),Toast.LENGTH_SHORT);
    }  
  
    public void toastLong(String msg) {
        showToast(mContext, msg, Toast.LENGTH_LONG);  
    } 
    
    public void toastLong(@StringRes int strRes) {
        showToast(mContext, mResources.getString(strRes),Toast.LENGTH_LONG);
    }

    public static void showToast(Context context, String msg, int duration){
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

    public static void showToast(Context context, String msg, int duration,int gravity){
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}