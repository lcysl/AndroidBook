package com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;
import com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.callback.KnowledgeSystemCallback;
import com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.model.KnowledgeSystemModel;
import com.uestc.lcy.androidbook.modules.sort.SortFragment;

/**
 * Created by lcy on 2018\4\30 0030.
 */

public class KnowledgeSystemPresenter extends BasePresenter<SortFragment> implements KnowledgeSystemCallback {

    private KnowledgeSystemModel mModel;

    public KnowledgeSystemPresenter() {
        mModel = new KnowledgeSystemModel();
    }

    public void loadKnoledgeSystem() {
        mView.showLoading();
        mModel.loadKnowledgeSystem(this);
    }

    @Override
    public void onLoadKnowledgeSystemSuccess(KnowledgeSystemBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadKnowledgeSystemSuccess(bean);
        }
    }

    @Override
    public void onLoadKnowledgeSystemError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadKnowledgeSystemError();
        }
    }
}
