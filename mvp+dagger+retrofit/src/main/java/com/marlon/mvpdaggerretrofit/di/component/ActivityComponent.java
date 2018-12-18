package com.marlon.mvpdaggerretrofit.di.component;

import android.app.Activity;

import com.marlon.mvpdaggerretrofit.MvpMainActivity;
import com.marlon.mvpdaggerretrofit.di.module.ActivityModule;
import com.marlon.mvpdaggerretrofit.di.scope.ActivityScope;

import dagger.Component;

/**
 * @desc ActivityComponent
 * @author Marlon
 * @date 2018/12/18
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MvpMainActivity mainActivity);


}
