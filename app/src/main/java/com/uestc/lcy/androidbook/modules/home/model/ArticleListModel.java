package com.uestc.lcy.androidbook.modules.home.model;

import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.modules.home.callback.ArticleListCallback;
import com.uestc.lcy.androidbook.modules.home.service.ArticleListService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lcy on 2018\4\24 0024.
 */

public class ArticleListModel {
    /**
     * 加载文章列表
     * @param page
     */
    public void loadArticleList(int page ,final ArticleListCallback callback) {
        ArticleListService articleListService = HttpUtils.getInstance().create(ArticleListService.class);
        articleListService.loadArticleList(page)
                .enqueue(new Callback<ArticleListService>() {
                    @Override
                    public void onResponse(Call<ArticleListService> call, Response<ArticleListService> response) {
                        ArticleListBean bean = (ArticleListBean) response.body();
                        callback.onLoadArticleListSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<ArticleListService> call, Throwable t) {
                        callback.onLoadArticleListError();
                    }
                });
    }
}
