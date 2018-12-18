package com.marlon.mvpdaggerretrofit.di.module;

import android.app.Activity;

import com.marlon.mvpdaggerretrofit.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @desc ActivityModule
 * @author Marlon
 * @date 2018/12/18
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }


}
