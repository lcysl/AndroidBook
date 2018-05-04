package com.uestc.lcy.androidbook.modules.project.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.ProjectSortBean;
import com.uestc.lcy.androidbook.modules.project.ProjectFragment;
import com.uestc.lcy.androidbook.modules.project.callback.ProjectSortCallback;
import com.uestc.lcy.androidbook.modules.project.model.ProjectModel;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public class ProjectSortPresenter extends BasePresenter<ProjectFragment> implements ProjectSortCallback{

    private ProjectModel mModel;

    public ProjectSortPresenter() {
        mModel = new ProjectModel();
    }

    public void loadProjectSort() {
        mView.showLoading();
        mModel.loadProjectSort(this);
    }

    @Override
    public void onLoadProjectSortSuccess(ProjectSortBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadProjectSortSuccess(bean);
        }
    }

    @Override
    public void onLoadProjectSortError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadProjectSortError();
        }
    }
}
