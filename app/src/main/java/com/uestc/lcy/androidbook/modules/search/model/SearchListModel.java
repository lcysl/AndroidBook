package com.uestc.lcy.androidbook.modules.search.model;

import com.uestc.lcy.androidbook.model.HotKeyBean;
import com.uestc.lcy.androidbook.model.SearchBean;
import com.uestc.lcy.androidbook.modules.search.callback.SearchListCallback;
import com.uestc.lcy.androidbook.modules.search.service.HotKeyService;
import com.uestc.lcy.androidbook.modules.search.service.SearchService;
import com.uestc.lcy.androidbook.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public class SearchListModel {
    /**
     * 加载搜索结果
     * @param page
     * @param k
     * @param callback
     */
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

    /**
     * 加载搜索热词
     * @param callback
     */
    public void loadHotKey(final SearchListCallback callback) {
        HotKeyService hotKeyService = HttpUtils.getInstance().create(HotKeyService.class);
        hotKeyService.loadHotKey()
                .enqueue(new Callback<HotKeyBean>() {
                    @Override
                    public void onResponse(Call<HotKeyBean> call, Response<HotKeyBean> response) {
                        HotKeyBean bean = response.body();
                        callback.onLoadHotKeySuccess(bean);
                    }

                    @Override
                    public void onFailure(Call<HotKeyBean> call, Throwable t) {
                        callback.onLoadHotKeyError();
                    }
                });
    }
}
