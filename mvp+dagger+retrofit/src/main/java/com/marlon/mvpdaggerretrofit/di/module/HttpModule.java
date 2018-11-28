package com.marlon.mvpdaggerretrofit.di.module;


import android.util.Log;

import com.marlon.mvpdaggerretrofit.app.App;
import com.marlon.mvpdaggerretrofit.retrofit.BaseApiService;
import com.marlon.mvpdaggerretrofit.retrofit.BaseInterceptor;
import com.marlon.mvpdaggerretrofit.retrofit.BaseUrlInterceptor;
import com.marlon.mvpdaggerretrofit.retrofit.CaheInterceptor;
import com.marlon.mvpdaggerretrofit.retrofit.CookieManger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 * @author Marlon
 * @date 2017/2/26
 */

@Module
public class HttpModule {
    private static final String TAG = "HttpModule";
    private static final long TIMEOUT = 1000;

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        CookieManger cookieJar =
                new CookieManger(App.getInstance());
        File mFile = new File(App.getInstance().getCacheDir() + "http");//储存目录
        long maxSize = 10 * 1024 * 1024; // 10 MB 最大缓存数
        Cache mCache = new Cache(mFile, maxSize);
        Map<String, String> headers = new HashMap<>();
        return builder
                //添加Cookie管理，不需要管理可以不加，token在Cookie中的时候需要添加
                .cookieJar(cookieJar)
                //添加统一的请求头
                .addInterceptor(new BaseInterceptor(headers))
                //添加base改变拦截器
                .addInterceptor(new BaseUrlInterceptor())
                //添加缓存拦截器
                .addNetworkInterceptor(new CaheInterceptor(App.getInstance()))
                //打印请求信息（可以自定义打印的级别！！）
                .addNetworkInterceptor(new HttpLoggingInterceptor(message -> Log.e(TAG, message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                //相关请求时间设置
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                //添加缓存
                .cache(mCache)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
                .build();

    }


    @Singleton
    @Provides
    BaseApiService provideMyService(Retrofit retrofit) {
        return retrofit.create(BaseApiService.class);
    }


    @Singleton
    @Provides
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, BaseApiService.BASE_URL_ZHIHU);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder.baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
