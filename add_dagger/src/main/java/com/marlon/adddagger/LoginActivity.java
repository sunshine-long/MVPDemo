package com.marlon.adddagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.marlon.adddagger.app.App;
import com.marlon.adddagger.contract.LoginContract;
import com.marlon.adddagger.di.component.DaggerActivityComponent;
import com.marlon.adddagger.di.module.ActivityModule;
import com.marlon.adddagger.presenter.LoginPresenter;

import javax.inject.Inject;

/**
 * @desc LoginActivity
 * @author Marlon
 * @date 2018/10/9
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DaggerActivityComponent.builder().appComponent(App.getAppComponent()).activityModule(new ActivityModule(this)).build().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    public void gotoMain() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this.getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public void onClickLogin(View view) {
        mPresenter.login();
    }
}
