package com.uestc.lcy.androidbook.modules.home.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lcy on 2018\4\24 0024.
 */

public interface ArticleListService {
    @GET("article/list/{page}/json")
    Call<ArticleListService> loadArticleList(@Path("page") int page);
}
