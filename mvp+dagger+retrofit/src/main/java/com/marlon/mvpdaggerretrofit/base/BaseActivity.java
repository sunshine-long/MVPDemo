package com.marlon.mvpdaggerretrofit.base;


import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.di.component.ActivityComponent;
import com.marlon.mvpdaggerretrofit.di.component.DaggerActivityComponent;
import com.marlon.mvpdaggerretrofit.di.module.ActivityModule;

import javax.inject.Inject;

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract void initInject();

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/
}
