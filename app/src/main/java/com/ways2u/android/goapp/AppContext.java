package com.ways2u.android.goapp;

import android.app.Application;
import android.content.Context;

import com.ways2u.android.common.LogUtil;
import com.ways2u.android.goapp.dagger.ApiModule;
import com.ways2u.android.goapp.dagger.AppComponent;
import com.ways2u.android.goapp.dagger.AppModule;
import com.ways2u.android.goapp.dagger.config.ConfigModule;
import com.ways2u.android.goapp.dagger.DaggerAppComponent;
import com.ways2u.android.goapp.dagger.SharedPreferencesModule;
import com.ways2u.android.goapp.dagger.config.DaggerConfigComponent;
import com.ways2u.android.net.util.NetContext;

import javax.inject.Inject;

/**
 * Created by huanglong on 2016/12/5.
 */
//配置管理，用户信息管理，数据库管理，网络管理，缓存管理，日志管理
//等等....
public class AppContext extends Application {
    //他是全局的，所以@ApplicationScope 也是全局的，和创造的生命周期一致，
    //在作用域内是单例
    private AppComponent appComponent;

    //可以搞一个配置组件，在这里初始化注入
    //private ConfigModule configModule;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .configComponent(DaggerConfigComponent.builder()
                        .configModule(new ConfigModule())
                        .build())
                .appModule(new AppModule(this))
                .sharedPreferencesModule(new SharedPreferencesModule())
                .apiModule(new ApiModule())
                .build();

        NetContext.init(this);
        LogUtil.init(BuildConfig.LOG_DEBUG);
    }
/*
    @Inject
    public void setConfigModule(ConfigModule configModule)
    {
        this.configModule = configModule;
    }
*/
    public static AppContext get(Context context){
        return (AppContext)context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
