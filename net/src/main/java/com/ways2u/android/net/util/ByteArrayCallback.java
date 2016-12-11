package com.ways2u.android.net.util;

import org.json.JSONObject;

/**
 * Created by huanglong on 16/8/18.
 */
public interface ByteArrayCallback {
    public void onFailure(Throwable e, String errorResponse);
    public void onSuccess(byte[] response);
    public void onStart();
    public void onFinish();
}
