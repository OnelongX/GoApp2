package com.ways2u.android.goapp;

import org.json.JSONObject;

/**
 * Created by huanglong on 2016/12/9.
 */

public interface UICallCack<T> {
    public void onStart() ;
    public void onFailure(Throwable e, String errorResponse);
    public void onSuccess(T response);
    public void onFinish();

}
