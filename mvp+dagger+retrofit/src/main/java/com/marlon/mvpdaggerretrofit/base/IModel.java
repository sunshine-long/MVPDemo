package com.marlon.mvpdaggerretrofit.base;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author Marlon
 * @desc
 * @date 2019/6/18
 */
public interface IModel {
     void addDisposable(Disposable disposable);
     void addSubscribe(Observable<?> observable, BaseObserver observer);
     void onDetach();
}
