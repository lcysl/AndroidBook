package com.uestc.lcy.androidbook.modules.knowledge_system.service;

import com.uestc.lcy.androidbook.model.ChildArticleListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public interface ChildArticleListService {
    @GET("article/list/{id}/json")
    Call<ChildArticleListBean> loadChildArticleList(@Path("id") int id, @Query("cid") int cid);
}
