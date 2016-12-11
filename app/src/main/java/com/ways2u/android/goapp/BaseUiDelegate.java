package com.ways2u.android.goapp;

import android.view.View;
import android.widget.Toast;

import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.net.util.NetContext;

/**
 * Created by huanglong on 2016/12/9.
 */

public class BaseUiDelegate implements ILifecycleDelegate, IViewDelegate, IHUDDelegate, ILoaderDelegate {
    private Toast toast;
    public BaseActivity context;

    private BaseUiDelegate(BaseActivity context) {
        this.context = context;
    }

    public static BaseUiDelegate create(BaseActivity context) {
        return new BaseUiDelegate(context);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {
        //退出排队的请求
        if (NetContext.getInstance() != null)
            NetContext.getInstance().cancelAll(this);
    }

    @Override
    public void visible(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void gone(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void inVisible(View view) {
        if (view != null) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void enableView(View v) {
        if (v != null) {
            v.setEnabled(true);
        }
    }

    @Override
    public void disableView(View v) {
        if (v != null) {
            v.setEnabled(false);
        }
    }

    @Override
    public void toastShort(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }

    @Override
    public void toastLong(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setText(msg);
        toast.show();
    }

    @Override
    public void showLoadingDlg() {

    }

    @Override
    public void dismissLoadingDlg() {

    }

    @Override
    public void showPromtDlg() {

    }

    @Override
    public void dimissPromtDlg() {

    }

    @Override
    public void showAlertDlg() {

    }

    @Override
    public void dimissAlertDlg() {

    }
}
