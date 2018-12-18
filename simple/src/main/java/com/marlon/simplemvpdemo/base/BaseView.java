package com.marlon.simplemvpdemo.base;

/**
 * @author Marlon
 * @desc View 基类
 * @date 2018/10/8
 */
public interface BaseView<T extends BasePresenter>{
    //用于关联 Presenter
    void setPresenter(T presenter);
    //通用方法接口
    void showToast(String msg);
}
