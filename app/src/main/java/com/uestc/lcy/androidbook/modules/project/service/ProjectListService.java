package com.uestc.lcy.androidbook.modules.project.service;

import com.uestc.lcy.androidbook.model.ProjectListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public interface ProjectListService {
    @GET("project/list/{page}/json")
    Call<ProjectListBean> loadProjectList(@Path("page") int page, @Query("cid") int cid);
}
