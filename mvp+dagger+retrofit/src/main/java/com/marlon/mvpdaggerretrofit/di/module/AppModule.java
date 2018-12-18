package com.marlon.mvpdaggerretrofit.di.module;


import com.marlon.mvpdaggerretrofit.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @desc AppModule
 * @author Marlon
 * @date 2018/12/18
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

}
