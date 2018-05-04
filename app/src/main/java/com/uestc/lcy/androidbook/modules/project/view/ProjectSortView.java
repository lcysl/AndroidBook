package com.uestc.lcy.androidbook.modules.project.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.ProjectSortBean;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public interface ProjectSortView extends BaseView {
    void onLoadProjectSortSuccess(ProjectSortBean bean);
    void onLoadProjectSortError();
}
