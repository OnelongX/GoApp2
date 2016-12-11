package com.ways2u.android.net.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by huanglong on 2016/12/5.
 */

public class NetContext {
    private static NetContext mNetContext;
    private RequestQueue mRequestQueue;

    private NetContext(Context context){
        if (mRequestQueue != null)
            mRequestQueue.stop();
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 初始化，在App onCreate 里面使用
     * @param context
     */
    public static void init(Context context){
        mNetContext = new NetContext(context);
    }

    public static NetContext getInstance() {
        return mNetContext;
    }

    public void addRequest(Request request) {
        mRequestQueue.add(request);
    }

    public void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
    public void stop() {
        mRequestQueue.stop();
    }


    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

}
