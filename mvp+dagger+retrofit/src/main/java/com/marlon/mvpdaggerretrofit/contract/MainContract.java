package com.marlon.mvpdaggerretrofit.contract;


import com.marlon.mvpdaggerretrofit.base.BasePresenter;
import com.marlon.mvpdaggerretrofit.base.BaseView;

/**
 * Created by KangLong on 2017/7/6.
 */

public class MainContract {
    public interface View extends BaseView {
        void showData(String s);
    }

    public interface Presenter extends BasePresenter<View> {
        void getVersion();

    }
}
