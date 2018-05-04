package com.uestc.lcy.androidbook.modules.knowledge_system.service;

import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lcy on 2018\4\30 0030.
 */

public interface KnowledgeSystemService {
    @GET("tree/json")
    Call<KnowledgeSystemBean> loadKnowledgeSystem();
}
