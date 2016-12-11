package com.ways2u.android.goapp;

/**
 * Created by huanglong on 2016/12/9.
 */

public interface IMessageHandler<T> {

    public T obtainMessage(int what, Object obj);
    public void postMessage(T msg);
    public void handleMessage(T msg);



    public void destory();
}
