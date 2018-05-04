package com.uestc.lcy.androidbook.modules.project.service;

import com.uestc.lcy.androidbook.model.ProjectSortBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public interface ProjectSortService {
    @GET("project/tree/json")
    Call<ProjectSortBean> loadProjectSort();
}
