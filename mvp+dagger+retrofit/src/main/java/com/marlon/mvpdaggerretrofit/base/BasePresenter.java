package com.marlon.mvpdaggerretrofit.base;

import android.arch.lifecycle.LifecycleObserver;

/**
 * @author Marlon
 * @desc
 * @date 2019/6/18
 */
public abstract class BasePresenter<M extends IModel,V extends IView> implements IPresenter<V>, LifecycleObserver {

}
