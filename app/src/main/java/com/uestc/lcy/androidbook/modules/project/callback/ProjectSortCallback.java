package com.uestc.lcy.androidbook.modules.project.callback;

import com.uestc.lcy.androidbook.model.ProjectSortBean;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public interface ProjectSortCallback {
    void onLoadProjectSortSuccess(ProjectSortBean bean);
    void onLoadProjectSortError();
}
