package com.ways2u.android.goapp.util;

public interface CallbackListener {
        void onSuccess(Object data, String msg);
        void onFail(String msg);
    }