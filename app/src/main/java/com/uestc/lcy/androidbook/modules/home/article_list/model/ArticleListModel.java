package com.uestc.lcy.androidbook.modules.home.article_list.model;

import android.util.Log;

import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.model.BannerBean;
import com.uestc.lcy.androidbook.modules.home.article_list.callback.ArticleListCallback;
import com.uestc.lcy.androidbook.modules.home.article_list.service.ArticleListService;
import com.uestc.lcy.androidbook.modules.home.article_list.service.BannerService;
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

    /**
     * 加载banner
     */
    public void loadBanner(final ArticleListCallback callback) {
        BannerService bannerService = HttpUtils.getInstance().create(BannerService.class);
        bannerService.loadBanner()
                .enqueue(new Callback<BannerBean>() {
                    @Override
                    public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                        BannerBean bean = response.body();
                        callback.onLoadBannerSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<BannerBean> call, Throwable t) {
                        callback.onLoadBannerError();
                    }
                });
    }
}
