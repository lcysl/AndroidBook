package com.uestc.lcy.androidbook.modules.sort.knowledge_system.callback;

import com.uestc.lcy.androidbook.model.ChildArticleListBean;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public interface ChildArticleListCallback {
    void onLoadChildArticleListSuccess(ChildArticleListBean bean);
    void onLoadChildArticleListError();
}
