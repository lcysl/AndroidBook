package com.uestc.lcy.androidbook.modules.home.articlelist.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.model.BannerBean;
import com.uestc.lcy.androidbook.modules.home.HomeFragment;
import com.uestc.lcy.androidbook.modules.home.articlelist.callback.ArticleListCallback;
import com.uestc.lcy.androidbook.modules.home.articlelist.model.ArticleListModel;

/**
 * Created by Administrator on 2018\4\24 0024.
 */

public class ArticleListPresenter extends BasePresenter<HomeFragment> implements ArticleListCallback {

    private ArticleListModel mModel;

    public ArticleListPresenter() {
        mModel = new ArticleListModel();
    }

    /**
     * 调用M层的网络请求方法
     * @param page
     */
    public void loadArticleList(int page) {
        mView.showLoading();
        mModel.loadArticleList(page, this);
    }

    public void loadBanner() {
        mView.showLoading();
        mModel.loadBanner(this);
    }

    @Override
    public void onLoadArticleListSuccess(ArticleListBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadArticleListSuccess(bean);
        }
    }

    @Override
    public void onLoadArticleListError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadArticleListError();
        }
    }

    @Override
    public void onLoadMoreArticleListSuccess(ArticleListBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadMoreArticleListSuccess(bean);
        }
    }

    @Override
    public void onLoadBannerSuccess(BannerBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadBannerSuccess(bean);
        }
    }

    @Override
    public void onLoadBannerError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadBannerError();
        }
    }

}
