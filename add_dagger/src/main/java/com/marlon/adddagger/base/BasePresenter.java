package com.marlon.adddagger.base;

/**
 * @author Marlon
 * @desc
 * @date 2018/10/8
 */
public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}
