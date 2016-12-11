package com.ways2u.android.goapp.dagger.ui;


import android.app.Activity;

import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.goapp.dagger.AppComponent;
import com.ways2u.android.goapp.dagger.fragment.FragmentComponent;
import com.ways2u.android.goapp.dagger.fragment.FragmentModule;
import com.ways2u.android.goapp.dagger.ui.main.MainComponent;
import com.ways2u.android.goapp.dagger.ui.main.MainModule;
import com.ways2u.android.goapp.util.HUDUtil;

import dagger.Component;
//Activity 基础组件
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
//可以不继承
public interface ActivityComponent{
    BaseActivity getBaseActivityActivity();
    Activity getActivity();
    HUDUtil getHUDUtil();


    //注入的固定入口，名字不能变，参数是当前类型，不能是父类，接口，因为是编译时生成的
    MainComponent addSub(MainModule mainModule);
    FragmentComponent addSub(FragmentModule fragmentModule);
}