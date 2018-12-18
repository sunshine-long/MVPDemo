package com.marlon.mvpdaggerretrofit.base;

/**
 * @desc BasePresenter
 * @author Marlon
 * @date 2018/12/18
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
