package com.marlon.mvpdaggerretrofit.contract;


import com.marlon.mvpdaggerretrofit.base.BasePresenter;
import com.marlon.mvpdaggerretrofit.base.BaseView;

/**
 * @desc MainContract
 * @author Marlon
 * @date 2018/12/18
 */

public class MainContract {
    public interface View extends BaseView {
        void showData(String s);
    }

    public interface Presenter extends BasePresenter<View> {
        void getVersion();

    }
}
