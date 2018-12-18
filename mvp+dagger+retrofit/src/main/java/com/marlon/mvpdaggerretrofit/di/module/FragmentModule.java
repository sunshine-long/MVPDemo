package com.marlon.mvpdaggerretrofit.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.marlon.mvpdaggerretrofit.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @desc FragmentModule
 * @author Marlon
 * @date 2018/12/18
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
