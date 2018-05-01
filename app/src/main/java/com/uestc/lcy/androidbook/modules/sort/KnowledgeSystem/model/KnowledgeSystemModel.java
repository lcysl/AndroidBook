package com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.model;

import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;
import com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.callback.KnowledgeSystemCallback;
import com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.service.KnowledgeSystemService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lcy on 2018\4\30 0030.
 */

public class KnowledgeSystemModel {
    /**
     * 加载知识体系列表
     * @param callback
     */
    public void loadKnowledgeSystem(final KnowledgeSystemCallback callback) {
        KnowledgeSystemService knowledgeSystemService = HttpUtils.getInstance().create(KnowledgeSystemService.class);
        knowledgeSystemService.loadKnowledgeSystem()
                .enqueue(new Callback<KnowledgeSystemBean>() {
                    @Override
                    public void onResponse(Call<KnowledgeSystemBean> call, Response<KnowledgeSystemBean> response) {
                        KnowledgeSystemBean bean = response.body();
                        callback.onLoadKnowledgeSystemSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<KnowledgeSystemBean> call, Throwable t) {
                        callback.onLoadKnowledgeSystemError();
                    }
                });
    }
}
