package com.architecturedemo.http;

import android.util.Log;

import com.architecturedemo.CcsApplication;
import com.architecturedemo.Constant;
import com.architecturedemo.interceptor.AddCookiesInterceptor;
import com.architecturedemo.interceptor.ReceivedCookiesInterceptor;
import com.architecturedemo.ssl.SafeHostnameVerifier;
import com.architecturedemo.ssl.SafeTrustManager;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 普通的网络请求用到的Retrofit
 */

public class RequetRetrofit {
    private static final String TAG = "RequetRetrofit";
     /**
     * 创建okhttp相关对象
     */
    private static OkHttpClient okHttpClient;
    /**
     * 创建Retrofit相关对象
     */
    private static Retrofit retrofit;

    public static ApiService getInstance() {
        if (okHttpClient == null) {
            /**
             * 创建okhttp相关对象
             */
            okHttpClient = new OkHttpClient.Builder()

                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {   //访问网络请求，和服务端响应请求时。将数据拦截并输出
                            Log.e(TAG, "log: " + message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))     //Log等级
                    .connectTimeout(Constant.Server.TIME_OUT, TimeUnit.SECONDS)       //超时时间
                    .readTimeout(Constant.Server.TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(Constant.Server.TIME_OUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new AddCookiesInterceptor()) //这部分
                    .addInterceptor(new ReceivedCookiesInterceptor()) //这部分
                    .hostnameVerifier(new SafeHostnameVerifier())
                    .sslSocketFactory(CcsApplication.getSslSocket(),new SafeTrustManager())
                    //
                    .build();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.Server.ROOT_URL)         //BaseUrl
                    .client(okHttpClient)                       //请求的网络框架
                    .addConverterFactory(GsonConverterFactory.create())     //解析数据格式
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
                    .build();
        }
        return retrofit.create(ApiService.class);
    }



}
