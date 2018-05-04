package com.uestc.lcy.androidbook.modules.project.model;

import com.uestc.lcy.androidbook.model.ProjectSortBean;
import com.uestc.lcy.androidbook.modules.project.callback.ProjectSortCallback;
import com.uestc.lcy.androidbook.modules.project.service.ProjectSortService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public class ProjectSortModel {
    /**
     * 加载项目分类列表
     * @param callback
     */
    public void loadProjectSort(final ProjectSortCallback callback) {
        ProjectSortService projectSortService = HttpUtils.getInstance().create(ProjectSortService.class);
        projectSortService.loadProjectSort()
                .enqueue(new Callback<ProjectSortBean>() {
                    @Override
                    public void onResponse(Call<ProjectSortBean> call, Response<ProjectSortBean> response) {
                        ProjectSortBean bean = response.body();
                        callback.onLoadProjectSortSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<ProjectSortBean> call, Throwable t) {
                        callback.onLoadProjectSortError();
                    }
                });
    }
}
