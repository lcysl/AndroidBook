package com.uestc.lcy.androidbook.modules.knowledge_system.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.ChildArticleListBean;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public interface ChildArticleListView extends BaseView {
    void onLoadChildArticleListSuccess(ChildArticleListBean bean);
    void onLoadChildArticleListError();
}
