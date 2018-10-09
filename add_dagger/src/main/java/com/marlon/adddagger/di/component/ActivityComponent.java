package com.marlon.adddagger.di.component;

import android.app.Activity;

import com.marlon.adddagger.LoginActivity;
import com.marlon.adddagger.MainActivity;
import com.marlon.adddagger.di.module.ActivityModule;
import com.marlon.adddagger.di.scope.ActivityScope;

import dagger.Component;

/**
 * @desc ActivityComponent 用于管理需要进行依赖注入的Activity
 * @author Marlon
 * @date 2018/10/9
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);//用于注入MainActivity
    void inject(LoginActivity loginActivity);//用于注入LoginActivity
    //TODO 后续需要注入的Activity类都可以在这里添加 中像上面一样写就好了
}
