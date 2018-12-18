package com.marlon.mvpdaggerretrofit.di.component;


import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.di.module.AppModule;
import com.marlon.mvpdaggerretrofit.di.module.HttpModule;
import com.marlon.mvpdaggerretrofit.retrofit.BaseApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @desc AppComponent
 * @author Marlon
 * @date 2018/12/18
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    BaseApiService retrofitHelper();  //提供http的帮助类

}
