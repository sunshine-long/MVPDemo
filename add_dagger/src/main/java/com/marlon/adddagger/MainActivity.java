package com.marlon.adddagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marlon.adddagger.app.App;
import com.marlon.adddagger.di.component.DaggerActivityComponent;
import com.marlon.adddagger.di.module.ActivityModule;

/**
 * @desc MainActivity
 * @author Marlon
 * @date 2018/10/9
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerActivityComponent.builder().appComponent(App.getAppComponent()).activityModule(new ActivityModule(this)).build().inject(this);
    }
}
