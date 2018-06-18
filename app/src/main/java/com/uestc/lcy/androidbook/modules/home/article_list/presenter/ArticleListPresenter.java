package com.uestc.lcy.androidbook.modules.home.article_list.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.model.BannerBean;
import com.uestc.lcy.androidbook.modules.collection.callback.CollectionCallback;
import com.uestc.lcy.androidbook.modules.collection.model.CollectionModel;
import com.uestc.lcy.androidbook.modules.home.HomeFragment;
import com.uestc.lcy.androidbook.modules.home.article_list.callback.ArticleListCallback;
import com.uestc.lcy.androidbook.modules.home.article_list.model.ArticleListModel;

/**
 * Created by Administrator on 2018\4\24 0024.
 */

public class ArticleListPresenter extends BasePresenter<HomeFragment> implements ArticleListCallback,CollectionCallback {

    private ArticleListModel mModel;
    private CollectionModel collectionModel;

    public ArticleListPresenter() {
        mModel = new ArticleListModel();
        collectionModel = new CollectionModel();
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

    /**
     * 收藏
     * @param title
     * @param author
     * @param link
     */
    public void collection(String title, String author, String link) {
        mView.showLoading();
        collectionModel.collection(title,author, link, this);
    }

    /**
     * 取消收藏
     * @param articleId
     */
    public void cancelCollection(int articleId) {
        mView.showLoading();
        collectionModel.cancelCollection(articleId, this);
    }

    @Override
    public void onCollectionSuccess() {
        if(mView != null) {
            mView.hideLoading();
            mView.onCollectionSuccess();
        }
    }

    @Override
    public void onCollectionError() {
        if(mView != null) {
            mView.hideLoading();
            mView.onCollecionError();
        }
    }

    @Override
    public void onCancelCollectionSuccess() {
        if(mView != null) {
            mView.hideLoading();
            mView.onCancelCollectionSuccess();
        }
    }

    @Override
    public void onCancelCollectionError() {
        if(mView != null) {
            mView.hideLoading();
            mView.onCancelCollecionError();
        }
    }

}
