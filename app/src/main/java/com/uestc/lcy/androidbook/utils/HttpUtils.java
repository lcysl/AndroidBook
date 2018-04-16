package com.uestc.lcy.androidbook.utils;

import android.util.Log;

import com.uestc.lcy.androidbook.data.Api;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 * Created by lcy on 2018\4\14 0014.
 */

public class HttpUtils {

    //单例类：懒汉式（双重锁）
    private static HttpUtils mInstance;

    private HttpUtils() {
        initRetrofit();
    }

    public static HttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils();
                }
            }
        }
        return mInstance;
    }

    private Retrofit mRetrofit;

    /**
     * 配置OkHttpClient
     *
     * @return
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(6000, TimeUnit.MILLISECONDS)
                .readTimeout(6000, TimeUnit.MILLISECONDS)
                .build();
        return mOkHttpClient;
    }

    /**
     * 配置Retrofit
     */
    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     *
     * @return 返回对应的网络接口类型
     */
    public <T> T create(Class<T> cla) {
        return mRetrofit.create(cla);
    }

}
