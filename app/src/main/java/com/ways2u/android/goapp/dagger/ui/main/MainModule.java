package com.ways2u.android.goapp.dagger.ui.main;

import com.ways2u.android.goapp.dagger.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/10.
 */
@Module
public class MainModule {

    @SubActivityScope
    @Provides
    public String getQQ(){
        return "3223232";
    }


}
