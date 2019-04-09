package com.marlon.simplemvpdemo.presenter;

import android.os.Looper;

import com.marlon.simplemvpdemo.contract.LoginContract;

/**
 * @author Marlon
 * @desc 创建LoginPresenter实现LoginContract的Presenter的方法
 * @date 2018/10/8
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void login() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Looper.prepare();
                    mView.showToast("登录成功");
                    mView.gotoMain();
                    Looper.loop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
