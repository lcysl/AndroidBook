package com.uestc.lcy.androidbook.modules.search.service;

import com.uestc.lcy.androidbook.model.SearchBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public interface SearchService {
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Call<SearchBean> loadSearchList(@Path("page") int page, @Field("k") String k);
}
