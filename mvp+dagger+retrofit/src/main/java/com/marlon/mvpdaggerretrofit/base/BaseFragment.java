package com.marlon.mvpdaggerretrofit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

/**
 * @desc BaseFragment
 * @author Marlon
 * @date 2018/12/18
 */
public abstract class BaseFragment<P extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected abstract void initInject();


    public void showMessage(String message) {
        showToast(message);
    }


    public void gotoMain() {

    }

    @Override
    public void gotoLogin() {

    }
}