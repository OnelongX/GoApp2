package com.ways2u.android.goapp.dagger.ui;

import android.app.Activity;

import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.goapp.util.HUDUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private final BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }
 
  @Provides
  @ActivityScope
  public BaseActivity provideBaseActivity() {
    return this.activity;
  }

  @Provides
  @ActivityScope
  public Activity provideActivity() {
    return this.activity;
  }
/*
  @Provides
  @ActivityScope
  public HUDUtil provideHUDUtil(Activity a){
    return new HUDUtil(a);
  }
  */
}