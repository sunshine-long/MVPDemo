package com.marlon.mvpdaggerretrofit;


import android.widget.TextView;

import com.marlon.mvpdaggerretrofit.base.BaseMvpActivity;
import com.marlon.mvpdaggerretrofit.contract.MainContract;
import com.marlon.mvpdaggerretrofit.presenter.MainPresenter;


/**
 * @desc MvpMainActivity
 * @author Marlon
 * @date 2018/12/18
 */
public class MvpMainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    private TextView textView;

    @Override
    protected int getLayout() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initEventAndData() {
        textView = findViewById(R.id.text);
        mPresenter.getVersion();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void gotoLogin() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showData(String value) {
        textView.setText(value);
    }

}
