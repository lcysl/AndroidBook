package com.uestc.lcy.androidbook.modules.home.articlelist.model;

import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.modules.home.articlelist.callback.ArticleListCallback;
import com.uestc.lcy.androidbook.modules.home.articlelist.service.ArticleListService;
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
    public void loadArticleList(final int page , final ArticleListCallback callback) {
        ArticleListService articleListService = HttpUtils.getInstance().create(ArticleListService.class);
        articleListService.loadArticleList(page)
                .enqueue(new Callback<ArticleListBean>() {
                    @Override
                    public void onResponse(Call<ArticleListBean> call, Response<ArticleListBean> response) {
                        ArticleListBean bean = response.body();
                        if (page == 0) {
                            callback.onLoadArticleListSuccess(bean);
                        } else {
                            callback.onLoadMoreArticleListSuccess(bean);
                        }

                    }

                    @Override
                    public void onFailure(Call<ArticleListBean> call, Throwable t) {
                        callback.onLoadArticleListError();
                    }
                });
    }
}
