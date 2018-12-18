package com.marlon.mvpdaggerretrofit.di.component;

import android.app.Activity;

import com.marlon.mvpdaggerretrofit.di.module.FragmentModule;
import com.marlon.mvpdaggerretrofit.di.scope.FragmentScope;

import dagger.Component;

/**
 * @desc FragmentComponent
 * @author Marlon
 * @date 2018/12/18
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


}
