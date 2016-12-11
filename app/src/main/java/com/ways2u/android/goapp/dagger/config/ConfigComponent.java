package com.ways2u.android.goapp.dagger.config;


import com.ways2u.android.goapp.AppContext;
import com.ways2u.android.goapp.dagger.ApiModule;



import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/9.
 */
@ConfigScope
@Component(modules = {ConfigModule.class})
public interface ConfigComponent {
    String getBaseUrl();
    boolean isDebug();
}
