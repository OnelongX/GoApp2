package com.ways2u.android.goapp.meizi.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.ways2u.android.common.LogUtil;
import com.ways2u.android.goapp.R;
import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.goapp.dagger.AppComponent;
import com.ways2u.android.goapp.dagger.qualifier.DefaultQualifier;
import com.ways2u.android.goapp.dagger.ui.ActivityComponent;
import com.ways2u.android.goapp.dagger.ui.ActivityModule;
import com.ways2u.android.goapp.dagger.ui.DaggerActivityComponent;
import com.ways2u.android.goapp.dagger.ui.main.MainModule;
import com.ways2u.android.goapp.meizi.fragment.MainFragment;
import com.ways2u.android.goapp.util.HUDUtil;
import com.ways2u.android.goapp.util.SharePreferencesUtil;
import com.ways2u.android.goapp.util.Validator;

import javax.inject.Inject;

/**
 *
 */
public class SubActivity extends BaseActivity {
    private MainFragment mainFragment;
    private Handler handler;


    private Activity activity;
    @Inject
    public void setActivity(Activity activity){
        this.activity = activity;
    }

    @Inject
    public Application application;

    @Inject
    public String QQ;

    @Inject
    public HUDUtil hudUtil;
    @Inject
    public Validator validator;
    @Inject
    public Validator validator1;

    @Inject
    @DefaultQualifier
    public SharePreferencesUtil preferencesUtil;

    @Override
    public void handleMessage(Message msg) {
        LogUtil.e(this,msg.obj.toString());
    }

    @Override
    protected void setupActivityComponent(ActivityComponent appComponent) {

        appComponent.addSub(new MainModule()).inject(this);

        LogUtil.i(this,activity+"");
        LogUtil.i(this,application+"");
        LogUtil.i(this,QQ+"");
        LogUtil.i(this,hudUtil+"");
        preferencesUtil.setAndCommit("xxx","one---long");
        LogUtil.i(this,preferencesUtil.getString("xxx"));

        LogUtil.i(this,validator.isEmpty("")+"");

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView() {
        mainFragment = new MainFragment();
        initFragment(mainFragment);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData(Bundle bundle) {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg =(Message)mainFragment.handler.obtainMessage(1,"Msg form Activity");
                mainFragment.handler.postMessage(msg);

                hudUtil.toastLong("hhhhhh");

            }
        },1000);
    }

    public void initFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (ft != null && fragment != null) {
            ft.add(R.id.main_content, fragment, "MainFragment");
            ft.commit();
        }
    }

}
