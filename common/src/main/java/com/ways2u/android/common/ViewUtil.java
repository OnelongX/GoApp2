package com.ways2u.android.common;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by huanglong on 2016/11/28.
 */

public class ViewUtil {

    public static <T extends View>  T getViewById(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    public static <T extends View> T getViewById(Activity activity, @IdRes int id) {
        return (T) activity.findViewById(id);
    }

}
