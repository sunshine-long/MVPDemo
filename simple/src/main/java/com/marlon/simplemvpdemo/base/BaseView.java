package com.marlon.simplemvpdemo.base;

/**
 * @author Marlon
 * @desc
 * @date 2018/10/8
 */
public interface BaseView<T extends BasePresenter>{
    void setPresenter(T presenter);
    void showToast(String msg);
}
