package com.ways2u.android.goapp;

import android.view.View;

/**
 * Created by huanglong on 2016/12/9.
 */

public interface IViewDelegate {
    public void visible(View view);
    public void gone(View view);
    public void inVisible(View view);
    public void enableView(View v);
    public void disableView(View v);
}
