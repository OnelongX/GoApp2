package com.ways2u.android.goapp.dagger;

import android.app.Application;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ways2u.android.goapp.dagger.config.ConfigModule;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huanglong on 2016/12/11.
 */
@Module
public class ApiModule {

    public ApiModule() {
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient(boolean isDebug) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isDebug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    @Provides
    @ApplicationScope
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @ApplicationScope
    public Retrofit provideRestAdapter(Application application, OkHttpClient okHttpClient, String baseUrl, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    @Provides
    @ApplicationScope
    public GankApi provideApiService(Retrofit restAdapter) {
        return restAdapter.create(GankApi.class);
    }

}
