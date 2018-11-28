package com.marlon.mvpdaggerretrofit.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author kanglong
 * @date 17/8/11
 * 无MVP的activity基类
 */
public abstract class SimpleActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        ActivityCollector.addActivity(this);
        initEventAndData();
    }

    protected void showToast(String meg) {
        Toast.makeText(this, meg, Toast.LENGTH_LONG).show();
    }


    protected void showLoadingDialog() {

    }

    protected void dissmissLoadingDialog() {

    }

    public void goToLogionActivity() {

    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
