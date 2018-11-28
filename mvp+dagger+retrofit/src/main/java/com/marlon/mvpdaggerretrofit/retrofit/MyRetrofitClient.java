package com.marlon.mvpdaggerretrofit.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author KangLong
 * date 2017/5/8
 * description 快速 构建Retrofit请求类
 */
public class MyRetrofitClient {

    public static String baseUrl = BaseApiService.BASE_URL_DAFULT;
    private static final long TIMEOUT = 10;
    private static final String TAG = "MyRetrofitClient";
    private Retrofit retrofit;
    private static Context mContext;
    private OkHttpClient mOkHttpClient;

    public final static Map<String, String> baseUrlMap = new HashMap<>();

    static {
        baseUrlMap.put("zhihu", BaseApiService.BASE_URL_ZHIHU);
        baseUrlMap.put("baidu", BaseApiService.BASE_URL_BAIDU);
    }

    public static MyRetrofitClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    private static MyRetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        return new MyRetrofitClient(context, url);
    }

    /**
     * 用于自定义url的请求和添加token的请求
     *
     * @param context 上下文
     * @param url     自定义baseUrl
     * @param headers 自定义添加的header
     * @return
     */
    private static MyRetrofitClient getInstance(Context context, String url, Map<String, String> headers) {
        if (context != null) {
            mContext = context;
        }
        return new MyRetrofitClient(context, url, headers);
    }

    /**
     * 创建内部类单利
     */
    private static class SingletonHolder {
        private static MyRetrofitClient INSTANCE = new MyRetrofitClient(mContext);
    }

    private MyRetrofitClient() {
    }

    /**
     * 构造函数，用于初试化
     *
     * @param context
     */
    private MyRetrofitClient(Context context) {
        this(context, null);
    }

    private MyRetrofitClient(Context context, String url) {
        this(context, url, null);
    }

    private MyRetrofitClient(Context context, String url, Map<String, String> headers) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
        File mFile = new File(context.getCacheDir() + "http");//储存目录
        long maxSize = 10 * 1024 * 1024; // 10 MB 最大缓存数
        Cache mCache = new Cache(mFile, maxSize);
        mOkHttpClient = new OkHttpClient.Builder()
                //添加Cookie管理，不需要管理可以不加，token在Cookie中的时候需要添加
                .cookieJar(new CookieManger(context.getApplicationContext()))
                //添加统一的请求头
                .addInterceptor(new BaseInterceptor(headers))
                //添加base改变拦截器
                .addInterceptor(new BaseUrlInterceptor())
                //添加缓存拦截器
                .addNetworkInterceptor(new CaheInterceptor(context))
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

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mOkHttpClient)
                //添加转换器String
                .addConverterFactory(ScalarsConverterFactory.create())
                //这里是转换器  这里采用Gson做转换器
                .addConverterFactory(GsonConverterFactory.create())
                //添加RXjava做适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 用于构建请求代理,BaseApiService中没有包含时可以用这个
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    /**
     * 通过代理构建接口
     *
     * @return
     */
    public BaseApiService createService() {
        return retrofit.create(BaseApiService.class);
    }
}
