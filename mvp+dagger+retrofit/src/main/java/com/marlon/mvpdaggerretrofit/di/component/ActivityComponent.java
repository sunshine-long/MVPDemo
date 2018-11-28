package com.marlon.mvpdaggerretrofit.di.component;

import android.app.Activity;

import com.marlon.mvpdaggerretrofit.MvpMainActivity;
import com.marlon.mvpdaggerretrofit.di.module.ActivityModule;
import com.marlon.mvpdaggerretrofit.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MvpMainActivity mainActivity);


}
