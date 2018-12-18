package com.marlon.mvpdaggerretrofit.base;

import android.content.Context;

import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.retrofit.BaseApiService;
import com.marlon.mvpdaggerretrofit.retrofit.RxHelper;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @desc RxPresenter 基于Rx的Presenter封装,控制订阅的生命周期
 * @author Marlon
 * @date 2018/12/18
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected BaseApiService apiService;
    protected Context mContext;
    protected T mView;
    private CompositeDisposable mCompositeDisposable;

    public RxPresenter(App mContext, BaseApiService apiService) {
        this.apiService = apiService;
        this.mContext = mContext;
    }

    /**
     * 取消注册 中断请求
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
    }

    //注册
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable.compose(RxHelper.io_main(mContext)).subscribeWith(observer));
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
