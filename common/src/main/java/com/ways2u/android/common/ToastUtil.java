package com.ways2u.android.common;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {
    private Context mContext;
    private Resources mResources;

    private ToastUtil() {

    }
    
    private ToastUtil(Context context){
        this.mContext = context;
        this.mResources = context.getResources();
    }
    
    public void showShortToast(String msg) {  
        showToast(mContext, msg, Toast.LENGTH_SHORT);  
    }  
  
    public void showShortToast(int strRes) {
        showShortToast(mResources.getString(strRes));
    }  
  
    public void showLongToast(String msg) {
        showToast(mContext, msg, Toast.LENGTH_LONG);  
    } 
    
    public void showLongToast(int strRes) {  
        showLongToast(mResources.getString(strRes));
    }

    public static void showShortToast(Context context,String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context,int strRes) {
        showShortToast(  context,context.getString(strRes));
    }

    public static void showLongToast(Context context,String msg) {
        showToast(context, msg, Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context context,int strRes) {
        showLongToast(context,context.getString(strRes));
    }

    public static void showToast(Context context, String msg, int duration){
        showToast(context, msg, duration,Gravity.CENTER);
    }

    public static void showToast(Context context, String msg, int duration,int gravity){
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}