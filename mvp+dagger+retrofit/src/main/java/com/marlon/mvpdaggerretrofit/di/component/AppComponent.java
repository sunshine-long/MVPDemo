package com.marlon.mvpdaggerretrofit.di.component;


import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.di.module.AppModule;
import com.marlon.mvpdaggerretrofit.di.module.HttpModule;
import com.marlon.mvpdaggerretrofit.retrofit.BaseApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    BaseApiService retrofitHelper();  //提供http的帮助类

}
