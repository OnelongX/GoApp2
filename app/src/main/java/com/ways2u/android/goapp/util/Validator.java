package com.ways2u.android.goapp.util;

import android.text.TextUtils;
import android.widget.EditText;

import com.ways2u.android.goapp.dagger.ApplicationScope;

import javax.inject.Inject;

/**
 * Created by huanglong on 2016/12/10.
 */
//@ApplicationScope
public class Validator {
    @Inject
    public Validator(){}

    public boolean isEmpty(CharSequence data){
        return TextUtils.isEmpty(data);
    }

    /**
     * 文本编辑器是否有输入内容
     *
     * @param et
     * @return true代表没有输入内容
     */
    public boolean checkEditContentIsNull(EditText et) {
        if (et == null) {
            return true;
        } else {
            String str = (et == null ? "" : et.getText().toString().trim());
            if (!str.equals("")) {
                return false;
            }
        }
        return true;
    }
}
