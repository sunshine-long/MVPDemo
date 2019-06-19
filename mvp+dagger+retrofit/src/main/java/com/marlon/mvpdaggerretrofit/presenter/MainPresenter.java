package com.marlon.mvpdaggerretrofit.presenter;

import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.base.BaseObserver;
import com.marlon.mvpdaggerretrofit.base.RxPresenter;
import com.marlon.mvpdaggerretrofit.bean.Resond;
import com.marlon.mvpdaggerretrofit.contract.MainContract;
import com.marlon.mvpdaggerretrofit.retrofit.BaseApiService;
import com.marlon.mvpdaggerretrofit.retrofit.RxHelper;

import javax.inject.Inject;

/**
 * @desc MainPresenter
 * @author Marlon
 * @date 2018/12/18
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter(App app, BaseApiService service) {
        super(app, service);
    }


    @Override
    public void getVersion() {
        //使用方式一
        addDisposable(apiService.getVerisionRxjava()
                .compose(RxHelper.io_main(mContext))
                .subscribeWith(new BaseObserver<Resond>() {
                    @Override
                    protected void onSuccess(Resond value) {
                        mView.showData(value.toString());
                    }


                    @Override
                    protected void onFailure(String message) {
                        mView.showData(message);
                    }
                }));
        //使用方式二
        addSubscribe(apiService.getVerisionRxjava(), new BaseObserver<Resond>() {

            @Override
            protected void onSuccess(Resond value) {

            }

            @Override
            protected void onFailure(String message) {

            }
        });
    }
}
