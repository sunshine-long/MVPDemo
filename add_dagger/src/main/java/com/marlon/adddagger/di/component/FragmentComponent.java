package com.marlon.adddagger.di.component;

import android.app.Activity;

import com.marlon.adddagger.di.module.FragmentModule;
import com.marlon.adddagger.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


}
