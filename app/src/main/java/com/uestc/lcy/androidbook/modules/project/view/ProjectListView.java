package com.uestc.lcy.androidbook.modules.project.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.ProjectListBean;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public interface ProjectListView extends BaseView{
    void onLoadProjectListSuccess(ProjectListBean bean);
    void onLoadProjectListError();
    void onLoadMoreProjectListSuccess(ProjectListBean bean);
}
