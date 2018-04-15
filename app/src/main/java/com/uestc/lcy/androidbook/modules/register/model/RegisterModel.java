package com.uestc.lcy.androidbook.modules.register.model;

import android.util.Log;

import com.uestc.lcy.androidbook.model.RegisterBean;
import com.uestc.lcy.androidbook.modules.register.callback.RegisterCallback;
import com.uestc.lcy.androidbook.modules.register.service.RegisterService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册Model
 * Created by lcy on 2018\4\14 0014.
 */

public class RegisterModel {

    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @param callback
     */
    public void register(String username, String password, String repassword, final RegisterCallback callback) {
        RegisterService service = HttpUtils.getInstance().create(RegisterService.class);
        service.register(username, password, repassword)
                .enqueue(new Callback<RegisterBean>() {
                    @Override
                    public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                        Log.e("RegisterModel.register", "onResponse");
                        RegisterBean bean = response.body();
                        callback.onRegisterSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<RegisterBean> call, Throwable t) {
                        Log.e("RegisterModel.register", t.getMessage());
                        callback.onRegisterError();

                    }
                });
    }
}
