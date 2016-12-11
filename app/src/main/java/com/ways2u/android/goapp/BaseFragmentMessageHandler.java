package com.ways2u.android.goapp;

import android.os.Handler;
import android.os.Message;

import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.goapp.base.BaseFragment;

import java.lang.ref.WeakReference;

/**
 * Created by huanglong on 2016/12/9.
 */

public class BaseFragmentMessageHandler implements IMessageHandler<Message> {
    /**
     *向其发消息，在undateU(msg)里面回调
     */
    public final MyHandler myHandler;

    public BaseFragmentMessageHandler(BaseFragment activity){
        myHandler = new MyHandler(activity);
    }

    @Override
    public Message obtainMessage(int what, Object obj) {
        return myHandler.obtainMessage(what,obj);
    }

    @Override
    public void postMessage(Message message) {
        myHandler.sendMessage(message);
    }

    @Override
    public void handleMessage(Message message) {
        //要Activity里面实现回调
        throw new RuntimeException("这个不能调用，要在本Fragment里面实现回调");
    }

    @Override
    public void destory(){
        if(myHandler!=null){
            myHandler.removeCallbacksAndMessages(null);
            myHandler.mActivity.clear();
        }
        //myHandler = null;
    }

    /**
     * 处理内存泄漏
     */
    public static class MyHandler extends Handler {
        WeakReference<BaseFragment> mActivity;
        MyHandler(BaseFragment activity) {
            mActivity = new WeakReference<BaseFragment>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (mActivity.get() != null) {
                mActivity.get().handleMessage(msg);
            }
        }
    }


}
