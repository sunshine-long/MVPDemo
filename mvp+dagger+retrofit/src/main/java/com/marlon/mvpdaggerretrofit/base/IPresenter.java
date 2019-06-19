package com.marlon.mvpdaggerretrofit.base;

/**
 * @desc BasePresenter
 * @author Marlon
 * @date 2018/12/18
 */
public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();
}
