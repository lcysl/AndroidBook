package com.uestc.lcy.androidbook.modules.project.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.ProjectListBean;
import com.uestc.lcy.androidbook.modules.project.activity.ProjectListActivity;
import com.uestc.lcy.androidbook.modules.project.callback.ProjectListCallback;
import com.uestc.lcy.androidbook.modules.project.model.ProjectModel;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public class ProjectListPresenter extends BasePresenter<ProjectListActivity> implements ProjectListCallback{

    private ProjectModel mModel;

    public ProjectListPresenter() {
        mModel = new ProjectModel();
    }

    public void loadProjectList(int page, int cid) {
        mView.showLoading();
        mModel.loadProjectList(page, cid, this);
    }

    @Override
    public void onLoadProjectListSuccess(ProjectListBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadProjectListSuccess(bean);
        }
    }

    @Override
    public void onLoadProjectListError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadProjectListError();
        }
    }

    @Override
    public void onLoadMoreProjectListSuccess(ProjectListBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadMoreProjectListSuccess(bean);
        }
    }
}
