package com.uestc.lcy.androidbook.modules.sort.knowledge_system.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.ChildArticleListBean;
import com.uestc.lcy.androidbook.modules.sort.knowledge_system.activity.ChildArticleListActivity;
import com.uestc.lcy.androidbook.modules.sort.knowledge_system.callback.ChildArticleListCallback;
import com.uestc.lcy.androidbook.modules.sort.knowledge_system.model.ChildArticleListModel;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public class ChildArticleListPresenter extends BasePresenter<ChildArticleListActivity> implements ChildArticleListCallback{

    private ChildArticleListModel mModel;

    public ChildArticleListPresenter() {
        mModel = new ChildArticleListModel();
    }

    public void loadChildArticleList(int firstId, int childrenId) {
        mView.showLoading();
        mModel.loadChildArticleList(firstId, childrenId, this);
    }

    @Override
    public void onLoadChildArticleListSuccess(ChildArticleListBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadChildArticleListSuccess(bean);
        }
    }

    @Override
    public void onLoadChildArticleListError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadChildArticleListError();
        }
    }
}
