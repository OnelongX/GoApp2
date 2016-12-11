package com.ways2u.android.common;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;


public class AlertDialogUtil {
	private static final String CONFIRM_ID = "确定";//R.string.nonetwork_sure;
	private static final String CANCEL_ID = "取消";//R.string.nonetwork_cancel;

	private AlertDialogUtil() {
	}

	public static void alert(Context context, String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(msg);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public static void alert(Context context, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public static void alert(Context context, int titleId, int msgId) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titleId).setMessage(msgId);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public static void alert(Context context, int msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public static void alert(Context context, String title, String msg,
			boolean canceledOnTouchOutside) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(msg);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		dialog.show();
	}

	public static void alert(Context context, int msg,
			boolean canceledOnTouchOutside) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		dialog.show();
	}


	public static void alert(Context context, String msg,
							 boolean canceledOnTouchOutside) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		dialog.show();
	}

	public static void alert(Context context, String msg,
			boolean canceledOnTouchOutside,
			DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton(CONFIRM_ID, listener);
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		dialog.show();
	}

	public static void alert(Context context, int titleId, int msgId,
			boolean canceledOnTouchOutside) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titleId).setMessage(msgId);
		builder.setPositiveButton(CONFIRM_ID,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		dialog.show();
	}
}
