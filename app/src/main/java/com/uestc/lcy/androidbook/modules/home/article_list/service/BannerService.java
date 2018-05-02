package com.uestc.lcy.androidbook.modules.home.article_list.service;

import com.uestc.lcy.androidbook.model.BannerBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lcy on 2018\4\27 0027.
 */

public interface BannerService {
    @GET("banner/json")
    Call<BannerBean> loadBanner();
}
