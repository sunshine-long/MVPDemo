package com.marlon.simplemvpdemo.presenter;

import com.marlon.simplemvpdemo.contract.LoginContract;

/**
 * @author Marlon
 * @desc 创建LoginPresenter实现LoginContract的Presenter的方法
 * @date 2018/10/8
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
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
}
