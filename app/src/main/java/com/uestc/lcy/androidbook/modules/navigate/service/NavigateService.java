package com.uestc.lcy.androidbook.modules.navigate.service;

import com.uestc.lcy.androidbook.model.NavigateBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public interface NavigateService {
    @GET("navi/json")
    Call<NavigateBean> loadNavigation();
}
