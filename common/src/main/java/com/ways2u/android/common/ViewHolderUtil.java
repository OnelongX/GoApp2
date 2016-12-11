package com.ways2u.android.common;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by huanglong on 2016/11/28.
 */

public class ViewHolderUtil {
    public static <T extends View>  T get(View view, @IdRes int id) {
        SparseArray<View> viewHolder = (SparseArray<View>)view.getTag();
        if(viewHolder==null){
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }

        View viewChild = viewHolder.get(id);
        if(viewChild==null){
            viewChild = view.findViewById(id);
            viewHolder.put(id,viewChild);
        }

        return (T) viewChild;
    }
}
