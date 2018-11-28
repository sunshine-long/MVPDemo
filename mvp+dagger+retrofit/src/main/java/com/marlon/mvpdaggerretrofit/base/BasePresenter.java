package com.marlon.mvpdaggerretrofit.base;

/**
 * Created by 康龙 on 2017/5/9.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
