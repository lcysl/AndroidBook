package com.uestc.lcy.androidbook.modules.navigate.model;

import com.uestc.lcy.androidbook.model.NavigateBean;
import com.uestc.lcy.androidbook.modules.navigate.callback.NavigateCallback;
import com.uestc.lcy.androidbook.modules.navigate.service.NavigateService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public class NavigateModel {
    /**
     * 请求导航数据
     * @param callback
     */
    public void loadNavigation(final NavigateCallback callback) {
        NavigateService navigateService = HttpUtils.getInstance().create(NavigateService.class);
        navigateService.loadNavigation()
                .enqueue(new Callback<NavigateBean>() {
                    @Override
                    public void onResponse(Call<NavigateBean> call, Response<NavigateBean> response) {
                        NavigateBean bean = response.body();
                        callback.onLoadNavigationSuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<NavigateBean> call, Throwable t) {
                        callback.onLoadNavigationError();
                    }
                });
    }
}
