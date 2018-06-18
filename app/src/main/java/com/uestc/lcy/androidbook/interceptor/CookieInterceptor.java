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

        if(cookie != "" && cookie != null) {
            Request compressedRequest = request.newBuilder()
                    .header("Cookie", cookie)
                    .build();
            response = chain.proceed(compressedRequest);
        } else {
            response = chain.proceed(chain.request());
            List<String> cookieStrList = response.headers("Set-Cookie");
            if(!cookieStrList.isEmpty()) {
                String cookieStr = cookieStrList.get(0).split(";")[0];
                CookieConfig.getInstance().setString("cookie", cookieStr);
            }
        }
        return response;
    }
}
