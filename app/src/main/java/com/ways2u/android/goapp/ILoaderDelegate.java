package com.ways2u.android.goapp;

/**
 * Created by huanglong on 2016/12/9.
 */

public interface ILoaderDelegate {
    public void showLoadingDlg();
    public void dismissLoadingDlg();

    public void showPromtDlg();
    public void dimissPromtDlg();

    public void showAlertDlg();
    public void dimissAlertDlg();
}
