package com.ways2u.android.goapp.dagger.fragment;

import com.ways2u.android.goapp.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/11.
 */

@Module
public class FragmentModule {
    private BaseFragment baseFragment;

    public FragmentModule(BaseFragment baseFragment){
        this.baseFragment = baseFragment;
    }

    @Provides
    @FragmentScope
    public BaseFragment provideBaseFragment() {
        return baseFragment;
    }
}
