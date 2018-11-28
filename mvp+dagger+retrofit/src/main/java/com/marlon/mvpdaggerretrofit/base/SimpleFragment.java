package com.marlon.mvpdaggerretrofit.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.di.component.DaggerFragmentComponent;
import com.marlon.mvpdaggerretrofit.di.component.FragmentComponent;
import com.marlon.mvpdaggerretrofit.di.module.FragmentModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by codeest on 16/8/11.
 * 无MVP的Fragment基类
 */

public abstract class SimpleFragment extends android.support.v4.app.Fragment {
    public final String TAG = this.getClass().getSimpleName();
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    protected void showToast(String meg) {
        Toast.makeText(App.getInstance(), meg, Toast.LENGTH_LONG).show();
    }

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}
