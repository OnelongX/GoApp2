package com.ways2u.android.goapp.dagger;

import android.app.Application;

import com.ways2u.android.goapp.util.Validator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/9.
 */
@Module
public class AppModule {

    private final Application application;

    @Provides
    @ApplicationScope
    public Application provideApplication() {
        return application;
    }
/*
    @Provides
    @ApplicationScope
    public Validator provideValidator(){
        return new Validator();
    }
*/
    public AppModule(Application application) {
        this.application = application;
    }

}
