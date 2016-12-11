package com.ways2u.android.goapp.util;

import com.ways2u.android.goapp.IFinishable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanglong on 2016/12/9.
 */

public class FinishableStack {

    /**
     * Activity list
     */
    private static final List<IFinishable> sfinishableList = new ArrayList<IFinishable>();

    public static void finishAll() {
        List<IFinishable> fList = new ArrayList<IFinishable>(sfinishableList);
        for (IFinishable f : fList) {
            f.exit();
        }
    }

    public static void finishAllExceptSelf(IFinishable finishable) {
        List<IFinishable> fList = new ArrayList<IFinishable>(sfinishableList);
        for (IFinishable f : fList) {
            if(!f.equals(finishable))
                f.exit();
        }
    }

    public static void putFinishList(IFinishable f) {
        sfinishableList.add(f);
    }

    public static void removeFromFinishList(IFinishable f) {
        sfinishableList.remove(f);
    }
}
