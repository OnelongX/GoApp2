package com.ways2u.android.goapp;

import android.os.Bundle;

/**
 * Created by huanglong on 2016/12/9.
 */

public interface ICreateCallback {
    public void initView();
    public void initData(Bundle savedInstanceState);
    public void setListener();
    public int getLayoutId();
}
