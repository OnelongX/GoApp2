package com.ways2u.android.common;



public class DoubleClickUtil {

    private static final long DEFAULT = 500L;
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < DEFAULT) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
