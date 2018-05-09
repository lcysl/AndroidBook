package com.uestc.lcy.androidbook.modules.search.service;

import com.uestc.lcy.androidbook.model.HotKeyBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lcy on 2018\5\9 0009.
 */

public interface HotKeyService {
    @GET("hotkey/json")
    Call<HotKeyBean> loadHotKey();
}
