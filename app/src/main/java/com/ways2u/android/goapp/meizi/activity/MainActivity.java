package com.ways2u.android.goapp.meizi.activity;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.ways2u.android.common.LogUtil;
import com.ways2u.android.goapp.R;
import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.goapp.dagger.GankApi;
import com.ways2u.android.goapp.dagger.qualifier.DefaultQualifier;
import com.ways2u.android.goapp.dagger.ui.ActivityComponent;
import com.ways2u.android.goapp.dagger.ui.ActivityModule;
import com.ways2u.android.goapp.dagger.ui.ActivityScope;
import com.ways2u.android.goapp.dagger.AppComponent;
import com.ways2u.android.goapp.dagger.ui.DaggerActivityComponent;
import com.ways2u.android.goapp.dagger.ui.main.MainModule;
import com.ways2u.android.goapp.meizi.fragment.MainFragment;
import com.ways2u.android.goapp.meizi.model.Meizi;
import com.ways2u.android.goapp.util.HUDUtil;
import com.ways2u.android.goapp.util.SharePreferencesUtil;
import com.ways2u.android.goapp.util.Validator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class MainActivity extends BaseActivity {
    private MainFragment mainFragment;
    private Handler handler;

    private Activity activity;

    @Inject
    public void setActivity(Activity activity) {
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
    GankApi gankApi;

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

        LogUtil.i(this, activity + "");
        LogUtil.i(this, application + "");
        LogUtil.i(this, hudUtil + "");
        LogUtil.i(this, gankApi + "");
        LogUtil.i(this, preferencesUtil + "");

        preferencesUtil.setAndCommit("xxx", "one---long");
        LogUtil.i(this, preferencesUtil.getString("xxx"));

        LogUtil.i(this, validator.isEmpty("") + "");

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
        //
    }

    @Override
    public void initData(Bundle bundle) {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg = (Message) mainFragment.handler.obtainMessage(1, "Msg form Activity");
                mainFragment.handler.postMessage(msg);

                hudUtil.toastLong("hhhhhh");

                gankApi.latest(10, 1)
                        .compose(MainActivity.this.<Meizi>bindUntilEvent(ActivityEvent.DESTROY))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Meizi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.i(this,  "onSubscribe");
                    }

                    @Override
                    public void onNext(Meizi value) {
                        LogUtil.i(this, value + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i(this,  e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.i(this,  "onComplete");
                    }
                });

            }
        }, 1000);
    }

    public void initFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (ft != null && fragment != null) {
            ft.add(R.id.main_content, fragment, "MainFragment");
            ft.commit();
        }
    }

}
