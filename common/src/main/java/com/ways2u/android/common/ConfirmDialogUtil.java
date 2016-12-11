package com.ways2u.android.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Onelong on 16/3/3.
 */
public class ConfirmDialogUtil {
    private static final String CONFIRM_ID = "确定";//R.string.nonetwork_sure;
    private static final String CANCEL_ID = "取消";//R.string.nonetwork_cancel;

    public static void confirm(Context context, String title, String msg,
                               DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(msg);
        builder.setPositiveButton(CONFIRM_ID, listener);
        builder.setNegativeButton(CANCEL_ID, null);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void confirm(Context context, String msg,
                               DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setPositiveButton(CONFIRM_ID, listener);
        builder.setNegativeButton(CANCEL_ID, null);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void confirm(Context context, int titleId, int msgId,
                               DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId).setMessage(msgId);
        builder.setPositiveButton(CONFIRM_ID, listener);
        builder.setNegativeButton(CANCEL_ID, null);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void confirm(Context context, String title, String msg,
                               boolean canceledOnTouchOutside,
                               DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(msg);
        builder.setPositiveButton(CONFIRM_ID, listener);
        builder.setNegativeButton(CANCEL_ID, null);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.show();
    }

    public static void confirm(Context context, int titleId, int msgId,
                               boolean canceledOnTouchOutside,
                               DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId).setMessage(msgId);
        builder.setPositiveButton(CONFIRM_ID, listener);
        builder.setNegativeButton(CANCEL_ID, null);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.show();
    }
}
