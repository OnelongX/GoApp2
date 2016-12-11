package com.ways2u.android.goapp.dagger.ui.main;

import com.ways2u.android.goapp.dagger.ui.ActivityScope;
import com.ways2u.android.goapp.meizi.activity.MainActivity;
import com.ways2u.android.goapp.meizi.activity.SubActivity;

import dagger.Subcomponent;

/**
 * Created by huanglong on 2016/12/10.
 */
@SubActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(SubActivity subActivity);
    String getQQ();
}
