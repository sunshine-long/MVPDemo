package com.marlon.adddagger.app;

import android.app.Application;

import com.marlon.adddagger.di.component.AppComponent;
import com.marlon.adddagger.di.component.DaggerAppComponent;
import com.marlon.adddagger.di.module.AppModule;


/**
 * @desc App
 * @author Marlon
 * @date 2018/10/9
 */
public class App extends Application {
    private static App instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
//                    .httpModule(new HttpModule())  创建网络请求的module这里暂时不管，
                    .build();
        }
        return appComponent;
    }

}
