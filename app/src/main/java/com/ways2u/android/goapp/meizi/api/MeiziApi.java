package com.ways2u.android.goapp.meizi.api;

import android.content.Context;

import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.net.util.JsonCallback;
import com.ways2u.android.net.util.NetContext;
import com.ways2u.android.net.util.OKHttpUtil;

/**
 * Created by huanglong on 2016/12/5.
 */

public class MeiziApi {

    public static void getMeiziList(BaseActivity context,int page,JsonCallback jsonCallback){
        new OKHttpUtil(context).get("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/"+page, jsonCallback);
    }
}
