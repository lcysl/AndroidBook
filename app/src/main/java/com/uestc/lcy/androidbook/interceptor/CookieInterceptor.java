package com.uestc.lcy.androidbook.interceptor;

import android.util.Log;

import com.uestc.lcy.androidbook.config.CookieConfig;
import java.io.IOException;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lcy on 2018\6\16 0016.
 */

public class CookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        String cookie = CookieConfig.getInstance().getString("cookie", "");

        Response response;
        Request request = chain.request();

        if(cookie != "" && cookie != null) {//本地存在cookie，证明是登录状态
            Request compressedRequest = request.newBuilder()
                    .header("Cookie", cookie)//把cookie设置到header，证明是登录状态
                    .build();
            response = chain.proceed(compressedRequest);
        } else {
            response = chain.proceed(chain.request());
            List<String> cookieStrList = response.headers("Set-Cookie");//获取到服务器返回的cookie值
            if(!cookieStrList.isEmpty()) {//不为空，证明存在cookie，登陆成功的情况
                String cookieStr = cookieStrList.get(0).split(";")[0];
                CookieConfig.getInstance().setString("cookie", cookieStr);//把cookie值存入SP中，持久化
            }
        }
        return response;
    }
}
