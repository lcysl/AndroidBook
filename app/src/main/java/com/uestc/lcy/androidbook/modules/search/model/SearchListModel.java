package com.uestc.lcy.androidbook.modules.search.model;

import com.uestc.lcy.androidbook.model.SearchBean;
import com.uestc.lcy.androidbook.modules.search.callback.SearchListCallback;
import com.uestc.lcy.androidbook.modules.search.service.SearchService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public class SearchListModel {
    public void loadSearchList(final int page, String k, final SearchListCallback callback) {
        SearchService searchService = HttpUtils.getInstance().create(SearchService.class);
        searchService.loadSearchList(page, k)
                .enqueue(new Callback<SearchBean>() {
                    @Override
                    public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                        SearchBean bean = response.body();
                        if (page == 0) {
                            callback.onLoadSearchListSuccess(bean);
                        } else {
                            callback.onLoadMoreSearchListSuccess(bean);
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchBean> call, Throwable t) {
                        callback.onLoadSearchListError();
                    }
                });
    }
}
