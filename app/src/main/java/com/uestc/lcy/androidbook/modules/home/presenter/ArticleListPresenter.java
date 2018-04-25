package com.uestc.lcy.androidbook.modules.home.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.modules.home.HomeFragment;
import com.uestc.lcy.androidbook.modules.home.callback.ArticleListCallback;
import com.uestc.lcy.androidbook.modules.home.model.ArticleListModel;

/**
 * Created by Administrator on 2018\4\24 0024.
 */

public class ArticleListPresenter extends BasePresenter<HomeFragment> implements ArticleListCallback {

    private ArticleListModel mModel;

    public ArticleListPresenter() {
        mModel = new ArticleListModel();
    }

    public void loadArticleList(int page) {
        mView.showLoading();
        mModel.loadArticleList(page, this);
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
}
