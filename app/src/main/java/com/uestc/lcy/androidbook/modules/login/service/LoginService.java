package com.uestc.lcy.androidbook.modules.login.service;

import com.uestc.lcy.androidbook.model.LoginBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 注册service
 * Created by lcy on 2018\4\16 0016.
 */

public interface LoginService {
    @POST("user/login")
    @FormUrlEncoded
    Call<LoginBean> login(@Field("username") String username,
                             @Field("password") String password);
}
