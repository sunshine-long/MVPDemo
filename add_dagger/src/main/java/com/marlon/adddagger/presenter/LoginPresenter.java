package com.marlon.adddagger.presenter;


import com.marlon.adddagger.LoginActivity;
import com.marlon.adddagger.contract.LoginContract;

import javax.inject.Inject;

/**
 * @author Marlon
 * @desc
 * @date 2018/10/8
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginActivity view;
    @Inject
    public LoginPresenter() {

    }

    @Override
    public void login() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    view.showToast("登录成功");
                    view.gotoMain();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public void attachView(LoginContract.View view) {
        this.view = (LoginActivity) view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
