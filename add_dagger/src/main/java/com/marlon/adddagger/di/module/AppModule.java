package com.marlon.adddagger.di.module;


import com.marlon.adddagger.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @desc AppModule
 * @author Marlon
 * @date 2018/10/9
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
