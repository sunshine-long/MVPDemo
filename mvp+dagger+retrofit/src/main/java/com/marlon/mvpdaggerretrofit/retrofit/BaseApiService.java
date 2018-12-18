package com.marlon.mvpdaggerretrofit.retrofit;


import com.marlon.mvpdaggerretrofit.base.BaseResponse;
import com.marlon.mvpdaggerretrofit.bean.Resond;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @desc BaseApiService   为Retrofit框架提供接口请求注解,也是我们的网络接口管理类
 * @author Marlon
 * @date 2018/12/18
 */

public interface BaseApiService {
    String BASE_URL_ZHIHU = " http://news-at.zhihu.com/";

    @GET("/api/4/version/android/2.3.0")
    Observable<BaseResponse<Resond>> getVerisionRxjava();
}



