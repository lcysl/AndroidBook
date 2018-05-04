package com.uestc.lcy.androidbook.modules.knowledge_system.model;

import com.uestc.lcy.androidbook.model.ChildArticleListBean;
import com.uestc.lcy.androidbook.modules.knowledge_system.callback.ChildArticleListCallback;
import com.uestc.lcy.androidbook.modules.knowledge_system.service.ChildArticleListService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public class ChildArticleListModel {
    /**
     * 加载二级标题下的文章列表
     */
    public void loadChildArticleList(int firstId, int childrenId, final ChildArticleListCallback callback) {
        ChildArticleListService childArticleListService = HttpUtils.getInstance().create(ChildArticleListService.class);
        childArticleListService.loadChildArticleList(firstId, childrenId)
                .enqueue(new Callback<ChildArticleListBean>() {
                    @Override
                    public void onResponse(Call<ChildArticleListBean> call, Response<ChildArticleListBean> response) {
                        ChildArticleListBean bean = response.body();
                        callback.onLoadChildArticleListSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<ChildArticleListBean> call, Throwable t) {
                        callback.onLoadChildArticleListError();
                    }
                });
    }
}
