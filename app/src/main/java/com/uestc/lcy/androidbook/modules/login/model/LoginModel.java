package com.uestc.lcy.androidbook.modules.login.model;

import com.uestc.lcy.androidbook.model.LoginBean;
import com.uestc.lcy.androidbook.modules.login.callback.LoginCallback;
import com.uestc.lcy.androidbook.modules.login.service.LoginService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录model
 * Created by lcy on 2018\4\16 0016.
 */

public class LoginModel {
    /**
     * 登录
     * @param username
     * @param password
     * @param callback
     */
    public void login(String username, String password, final LoginCallback callback) {
        LoginService loginService = HttpUtils.getInstance().create(LoginService.class);
        loginService.login(username, password)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        LoginBean bean = response.body();
                        callback.onLoginSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                        callback.onLoginError();
                    }
                });
    }
}
