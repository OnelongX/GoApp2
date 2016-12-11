package com.ways2u.android.common;

import com.socks.library.KLog;

/**
 * Created by huanglong on 2016/12/9.
 */

public class LogUtil {

    public static void init(boolean isShowlog) {
        KLog.init(isShowlog);
    }

    /**
     * @param tag
     * @param Message
     */
    public static void e(String tag, String Message) {
        if (Message != null && tag != null) {
            KLog.e(tag, Message);
        }
    }

    /**
     * @param tag
     * @param Message
     */
    public static void i(String tag, String Message) {
        if (Message != null && tag != null) {
            KLog.i(tag, Message);
        }
    }

    /**
     * @param tag
     * @param Message
     */
    public static void d(Object tag, String Message) {
        if (Message != null && tag != null) {
            KLog.d(tag.getClass().getName(), Message);
        }
    }


    /**
     * @param tag
     * @param Message
     */
    public static void e(Object tag, String Message) {
        if (Message != null && tag != null) {
            KLog.e(tag.getClass().getName(), Message);
        }
    }

    /**
     * @param tag
     * @param Message
     */
    public static void i(Object tag, String Message) {
        if (Message != null && tag != null) {
            KLog.i(tag.getClass().getName(), Message);
        }
    }

    /**
     * @param tag
     * @param Message
     */
    public static void d(String tag, String Message) {
        if (Message != null && tag != null) {
            KLog.d(tag.getClass().getName(), Message);
        }
    }

}
