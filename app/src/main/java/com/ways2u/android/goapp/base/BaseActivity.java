package com.ways2u.android.goapp.base;
/**
 * Created by Onelong on 2015/10/31.
 */

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.ways2u.android.common.LogUtil;
import com.ways2u.android.goapp.AppContext;
import com.ways2u.android.goapp.BaseActivityMessageHandler;
import com.ways2u.android.goapp.IAppDelegate;
import com.ways2u.android.goapp.ICreateCallback;
import com.ways2u.android.goapp.IFinishable;
import com.ways2u.android.goapp.BaseUiDelegate;


import com.ways2u.android.goapp.dagger.ui.ActivityComponent;
import com.ways2u.android.goapp.dagger.ui.ActivityModule;
import com.ways2u.android.goapp.dagger.ui.DaggerActivityComponent;
import com.ways2u.android.goapp.util.FinishableStack;
import com.ways2u.android.goapp.IMessageHandler;


public abstract class BaseActivity extends RxAppCompatActivity implements IFinishable,
        ICreateCallback {
    private static int sStartedActivityCount;
    //注入
    public BaseUiDelegate uiDelegate;
    //注入
    public IAppDelegate appDelegate;
    //注入，//用于组件直接通信
    public IMessageHandler handler;

    private String TAG;
    private ActivityComponent activityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getName();

        activityComponent = DaggerActivityComponent.builder()
                .appComponent(AppContext.get(this).getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();

        setupActivityComponent(activityComponent);
        getUiDelegate();
        handler = new BaseActivityMessageHandler(this);
        setContentView(getLayoutId());
        initView();
        setListener();
        initData(savedInstanceState);
        FinishableStack.putFinishList(this);
    }


    @Override
    public void finish() {
        FinishableStack.removeFromFinishList(this);
        super.finish();
    }

    @Override
    final public void exit() {
        try {
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sStartedActivityCount++;
        if (1 == sStartedActivityCount && appDelegate != null) {
            appDelegate.applicationDidEnterForeground();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sStartedActivityCount--;
        if (0 == sStartedActivityCount && appDelegate != null) {
            appDelegate.applicationDidEnterBackground();
        }
    }


    @Override
    protected void onDestroy() {

        if (handler != null) {
            handler.destory();
        }
        handler = null;

        if (uiDelegate != null) {
            uiDelegate.destory();
        }
        uiDelegate = null;

        super.onDestroy();
    }

    public abstract void handleMessage(Message msg);
    protected abstract void setupActivityComponent(ActivityComponent activityComponent);

    protected BaseUiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            uiDelegate = BaseUiDelegate.create(this);
        }
        return uiDelegate;
    }

    //-----------------------------------------------
    public <T extends View> T find(@IdRes int viewId) {
        return (T) this.findViewById(viewId);
    }

    /**
     * 获取输入框的文本
     *
     * @param e
     * @return
     */
    public String getEditTextString(EditText e) {
        String str;
        str = (e == null ? "" : e.getText().toString().trim());
        return str;
    }


    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}