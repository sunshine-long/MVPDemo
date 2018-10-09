package com.marlon.adddagger.di.component;


import com.marlon.adddagger.app.App;
import com.marlon.adddagger.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @desc AppComponent
 * @author Marlon
 * @date 2018/10/9
 */
@Singleton
@Component(modules = {AppModule.class/*, HttpModule.class*/})
public interface AppComponent {

    App getContext();  // 提供App的Context

//    BaseApiService retrofitHelper();  //提供http请求的帮助类这里暂时不管，后续讲

}
