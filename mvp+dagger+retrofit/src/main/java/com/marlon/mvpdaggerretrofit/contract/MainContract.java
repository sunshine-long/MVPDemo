package com.marlon.mvpdaggerretrofit.contract;


import com.marlon.mvpdaggerretrofit.base.IPresenter;
import com.marlon.mvpdaggerretrofit.base.IView;

/**
 * @desc MainContract
 * @author Marlon
 * @date 2018/12/18
 */

public class MainContract {
    public interface View extends IView {
        void showData(String s);
    }

    public interface Presenter extends IPresenter<View> {
        void getVersion();

    }

}
