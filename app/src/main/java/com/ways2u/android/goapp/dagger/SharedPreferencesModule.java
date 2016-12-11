package com.ways2u.android.goapp.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ways2u.android.goapp.dagger.ApplicationScope;
import com.ways2u.android.goapp.dagger.qualifier.CustomQualifier;
import com.ways2u.android.goapp.dagger.qualifier.DefaultQualifier;
import com.ways2u.android.goapp.util.SharePreferencesUtil;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/10.
 */
@Module
public class SharedPreferencesModule {

    //private final Application application;

    public SharedPreferencesModule(){
        //this.application = application;
    }
    
    @ApplicationScope
    @Provides
    @DefaultQualifier
    public SharedPreferences providerDefaultSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @ApplicationScope
    @Provides
    @CustomQualifier
    public SharedPreferences providerCustomSharedPreferences(Application application){
        return application.getSharedPreferences("custom", Context.MODE_PRIVATE);
    }

    @ApplicationScope
    @Provides
    @DefaultQualifier
    public SharePreferencesUtil providerDefaultSharePreferencesUtil(@DefaultQualifier SharedPreferences preferences){
        return new SharePreferencesUtil(preferences);
    }

    @ApplicationScope
    @Provides
    @CustomQualifier
    public SharePreferencesUtil providerCustomSharePreferencesUtil(@CustomQualifier SharedPreferences preferences){
        return new SharePreferencesUtil(preferences);
    }
}
