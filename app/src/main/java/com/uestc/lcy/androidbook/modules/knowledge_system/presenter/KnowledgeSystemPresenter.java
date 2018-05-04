package com.uestc.lcy.androidbook.modules.knowledge_system.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;
import com.uestc.lcy.androidbook.modules.knowledge_system.callback.KnowledgeSystemCallback;
import com.uestc.lcy.androidbook.modules.knowledge_system.model.KnowledgeSystemModel;
import com.uestc.lcy.androidbook.modules.knowledge_system.KnowledgeSystemFragment;

/**
 * Created by lcy on 2018\4\30 0030.
 */

public class KnowledgeSystemPresenter extends BasePresenter<KnowledgeSystemFragment> implements KnowledgeSystemCallback {

    private KnowledgeSystemModel mModel;

    public KnowledgeSystemPresenter() {
        mModel = new KnowledgeSystemModel();
    }

    public void loadKnowledgeSystem() {
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
