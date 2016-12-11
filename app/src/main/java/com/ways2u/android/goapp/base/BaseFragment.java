package com.ways2u.android.goapp.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.ways2u.android.goapp.BaseFragmentMessageHandler;
import com.ways2u.android.goapp.BaseUiDelegate;
import com.ways2u.android.goapp.ICreateCallback;
import com.ways2u.android.goapp.IMessageHandler;
import com.ways2u.android.goapp.dagger.ui.ActivityComponent;


public abstract class BaseFragment extends RxFragment implements ICreateCallback {
    //用于组件直接通信
    public IMessageHandler handler;
    private String TAG;
    //为将来注入
    public BaseUiDelegate uiDelegate;

    protected BaseActivity activity;
    private View rootView;

    protected LayoutInflater inflater;

    protected boolean hasLoad;//只加载一次
    protected boolean hasPrepared;//

    /**
     * @param title
     */
    public void setTitle(CharSequence title) {
        if (activity != null) {
            activity.setTitle(title);
        }
    }

    /**
     * @param resid
     */
    public void setTitle(int resid) {
        if (activity != null) {
            activity.setTitle(resid);
        }
    }

    protected BaseUiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            if (activity == null) {
                activity = (BaseActivity) getActivity();
            }
            uiDelegate = BaseUiDelegate.create(activity);
        }
        return uiDelegate;
    }

    protected void onInvisible() {
    }

    protected void onVisible() {
        if (hasPrepared && !hasLoad) {
            hasLoad = true;
            lazyLoad();
        }
    }

    /**
     * 子Activity 实现该方法 处理主线程UI
     *
     * @param msg
     */
    public abstract void handleMessage(Message msg);


    protected void lazyLoad() {
    }

    protected abstract void setupFragmentComponent(ActivityComponent fragmentComponent);

    public BaseActivity getBaseActivity()
    {
        return (BaseActivity)getActivity();
    }


    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (BaseActivity) getActivity();

        setupFragmentComponent(activity.getActivityComponent());

        getUiDelegate();
        TAG = getClass().getSimpleName();
        handler = new BaseFragmentMessageHandler(this);

        initView();
        setListener();
        initData(savedInstanceState);

        hasPrepared = true;
        if (getUserVisibleHint()) {//防止页面在不可见时加载
            onVisible();//第一个页面在执行onActivityCreated前就已调用setUserVisibleHint,对用户可见getUserVisibleHint=true,
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        if (uiDelegate != null) {
            uiDelegate.destory();
        }

        if (handler != null) {
            handler.destory();
        }

        uiDelegate = null;
        rootView = null;
        activity = null;
        handler = null;
        //fragmentComponent = null;
        super.onDestroy();
        //System.gc();
    }


    public <T extends View> T find(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }

    /**
     * 获取文本框的文本
     *
     * @param e
     * @return
     */
    public String getEditTextString(EditText e) {
        String str = "";
        str = (e == null ? "" : e.getText().toString().trim());
        return str;
    }



}
