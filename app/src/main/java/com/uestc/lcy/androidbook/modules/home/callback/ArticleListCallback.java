package com.uestc.lcy.androidbook.modules.home.callback;

import com.uestc.lcy.androidbook.model.ArticleListBean;

/**
 * Created by lcy on 2018\4\24 0024.
 */

public interface ArticleListCallback {
    void onLoadArticleListSuccess(ArticleListBean bean);
    void onLoadArticleListError();
}
