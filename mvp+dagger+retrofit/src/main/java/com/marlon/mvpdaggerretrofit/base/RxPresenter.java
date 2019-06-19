package com.marlon.mvpdaggerretrofit.base;

import android.content.Context;

import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.retrofit.BaseApiService;
import com.marlon.mvpdaggerretrofit.retrofit.RxHelper;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Marlon
 * @desc RxPresenter 基于Rx的Presenter封装,控制订阅的生命周期
 * @date 2018/12/18
 */
public class RxPresenter<V extends IView> implements IPresenter<V>, IModel {
    protected BaseApiService apiService;
    protected Context mContext;
    protected V mView;
    private CompositeDisposable mCompositeDisposable;

    public RxPresenter(App mContext, BaseApiService apiService) {
        this.apiService = apiService;
        this.mContext = mContext;
    }

    @Override
    public void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable.compose(RxHelper.io_main(mContext)).subscribeWith(observer));
    }


    @Override
    public void attachView(V view) {
        this.mView = view;
    }


    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onDetach() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        this.mView = null;
        onDetach();
    }
}
