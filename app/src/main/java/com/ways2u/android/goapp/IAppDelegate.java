package com.ways2u.android.goapp;

/**
 * Created by huanglong on 2016/12/9.
 */

public interface IAppDelegate {
    /**
     * Activity的回调函数。当application进入前台时，该函数会被自动调用。
     */
    public void applicationDidEnterForeground();

    /**
     * Activity的回调函数。当application进入后台时，该函数会被自动调用。
     */
    public void applicationDidEnterBackground();
}
