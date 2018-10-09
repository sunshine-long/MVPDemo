package com.marlon.adddagger.contract;


import com.marlon.adddagger.base.BasePresenter;
import com.marlon.adddagger.base.BaseView;

/**
 * @author Marlon
 * @desc
 * @date 2018/10/8
 */
public class LoginContract {
    public interface View extends BaseView {
        void gotoMain();
    }

    public interface Presenter extends BasePresenter<View> {
        void login();
    }
}
