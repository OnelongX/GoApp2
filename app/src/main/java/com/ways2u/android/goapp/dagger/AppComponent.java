package com.ways2u.android.goapp.dagger;

import android.app.Application;
import android.content.SharedPreferences;


import com.ways2u.android.goapp.AppContext;
import com.ways2u.android.goapp.dagger.config.ConfigComponent;
import com.ways2u.android.goapp.dagger.config.ConfigModule;
import com.ways2u.android.goapp.dagger.qualifier.CustomQualifier;
import com.ways2u.android.goapp.dagger.qualifier.DefaultQualifier;
import com.ways2u.android.goapp.util.SharePreferencesUtil;
import com.ways2u.android.goapp.util.Validator;

import dagger.Component;

/**
 * Created by huanglong on 2016/12/9.
 */
/*
* 暴露的api
* */
@ApplicationScope
@Component(modules = {AppModule.class, SharedPreferencesModule.class, ApiModule.class} ,dependencies = {ConfigComponent.class})
public interface AppComponent {
    void inject(AppContext appContext);
    Application getApplication();
    @DefaultQualifier
    SharedPreferences getDefaultSharedPreferences();
    @CustomQualifier
    SharedPreferences getCustomSharedPreferences();
    @DefaultQualifier
    SharePreferencesUtil getDefaultSharePreferencesUtil();
    @CustomQualifier
    SharePreferencesUtil getCustomSharePreferencesUtil();
    Validator getValidator();

    GankApi getGankApi();
}
