package com.ways2u.android.goapp;

/**
 * Created by huanglong on 2016/12/9.
 */

public interface ILoaderMulteTypeDelegate {
    public void showLoadingDlg(int type);
    public void dismissLoadingDlg(int type);

    public void showPromtDlg(int type);
    public void dimissPromtDlg(int type);

    public void showAlertDlg(int type);
    public void dimissAlertDlg(int type);
}
