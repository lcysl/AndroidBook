package com.uestc.lcy.androidbook.modules.register.service;

import com.uestc.lcy.androidbook.model.RegisterBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 注册 service
 * Created by lcy on 2018\4\14 0014.
 */

public interface RegisterService {

    @POST("user/register")
    @FormUrlEncoded
    Call<RegisterBean> register(@Field("username") String username,
                                @Field("password") String password,
                                @Field("repassword") String repassword);

}
