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
 * @desc HttpModule
 * @author Marlon
 * @date 2017/12/18
 */
@Module
public class HttpModule {
    private static final String TAG = "HttpModule";
    private static final long TIMEOUT = 1000;

    @Singleton
    @Provides
        //构建 Retrofit.Builder
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
        // 构建 OkHttpClient.Builder
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
        //构建 OkHttpClient 这里的拦截器 根据需求添加 需要才添加
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        CookieManger cookieJar =
                new CookieManger(App.getInstance());
        //储存目录
        File mFile = new File(App.getInstance().getCacheDir() + "http");
        // 10 MB 最大缓存数
        long maxSize = 10 * 1024 * 1024;
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
                //链接时间
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                //读取时间
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                //写入时间
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                //添加缓存
                .cache(mCache)
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .build();

    }

    @Singleton
    @Provides
        //构建 Retrofit
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, BaseApiService.BASE_URL_ZHIHU);
    }


    @Singleton
    @Provides
        //通过 反射机制 创建BaseApiService  这里BaseApiService 是封装请求方法的接口类
    BaseApiService provideMyService(Retrofit retrofit) {
        return retrofit.create(BaseApiService.class);
    }

    /**
     * 通过Retrofit.Builder OkHttpClient url 构建Retrofit
     * @param builder
     * @param client
     * @param url
     * @return
     */
    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder.baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
