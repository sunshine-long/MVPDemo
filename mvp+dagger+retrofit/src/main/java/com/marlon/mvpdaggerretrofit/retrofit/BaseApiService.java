package com.marlon.mvpdaggerretrofit.retrofit;


import com.marlon.mvpdaggerretrofit.base.BaseResponse;
import com.marlon.mvpdaggerretrofit.bean.Resond;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * 为Retrofit框架提供接口请求注解
 *
 * @author 康龙
 * @date 2017/5/9
 */

public interface BaseApiService {
    String BASE_URL_ZHIHU = " http://news-at.zhihu.com/";
    String BASE_URL_BAIDU = " https://www.baidu.com/";
    String BASE_URL_DAFULT = "http://www.sichuanxinge.com/";
    String URL_NAME = "url_name:";

    /**
     * (示例无参数请求)  若果baseUrl不需要修改 直接去掉 @Headers({URL_NAME + 相关url})
     * 这里采用rxjava做回调
     * 获取版本号
     *
     * @param
     * @return
     */
    @Headers({URL_NAME + BASE_URL_ZHIHU})
    @GET("/api/4/version/android/2.3.0")
    Observable<BaseResponse<Resond>> getVerisionRxjava();
}



