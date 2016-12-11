package com.ways2u.android.goapp.dagger.fragment;


import com.ways2u.android.goapp.base.BaseFragment;
import com.ways2u.android.goapp.dagger.ui.ActivityComponent;
import com.ways2u.android.goapp.meizi.fragment.MainFragment;


import dagger.Component;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
//可以不继承
public interface FragmentComponent {
    BaseFragment getBaseFragment();

    void inject(MainFragment mainFragment);
}