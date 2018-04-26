package com.uestc.lcy.androidbook.modules.home.articlelist.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.ArticleListBean;

/**
 * Created by lcy on 2018\4\24 0024.
 */

public interface ArticleListView extends BaseView{
    void onLoadArticleListSuccess(ArticleListBean bean);
    void onLoadArticleListError();
    void onLoadMoreArticleListSuccess(ArticleListBean bean);
}
