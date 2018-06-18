package com.uestc.lcy.androidbook.modules.collection.model;

import android.util.Log;

import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.model.BaseResponse;
import com.uestc.lcy.androidbook.model.FeedArticleListData;
import com.uestc.lcy.androidbook.modules.collection.callback.CollectionCallback;
import com.uestc.lcy.androidbook.modules.collection.callback.CollectionListCallback;
import com.uestc.lcy.androidbook.modules.collection.service.CollectionService;

import com.uestc.lcy.androidbook.utils.HttpUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 收藏model
 * Created by lcy on 2018\4\16 0016.
 */

public class CollectionModel {


    /**
     * 收藏
     * @param callback
     */
    public void collection(String title,String author , String link,final CollectionCallback callback) {
        CollectionService collectionService = HttpUtils.getInstance().create(CollectionService.class);
        collectionService.collection(title, author, link).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                callback.onCollectionSuccess();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onCollectionError();
            }
        });
    }

    /**
     * 取消收藏
     * @param callback
     */
    public void cancelCollection(int articleId, final CollectionCallback callback) {
        CollectionService collectionService = HttpUtils.getInstance().create(CollectionService.class);
        collectionService.cancelCollection(articleId).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                callback.onCancelCollectionSuccess();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onCancelCollectionError();
            }
        });
    }

    /**
     * 加载收藏列表
     */
    public void loadCollectionList(int page, final CollectionListCallback callback) {
        CollectionService collectionService = HttpUtils.getInstance().create(CollectionService.class);
        collectionService.loadCollectionList(page).enqueue(new Callback<BaseResponse<FeedArticleListData>>() {

            @Override
            public void onResponse(Call<BaseResponse<FeedArticleListData>> call, Response<BaseResponse<FeedArticleListData>> response) {
                BaseResponse<FeedArticleListData> baseResponse = response.body();
                callback.onLoadCollectionListSuccess(baseResponse.getData().getDatas());
            }

            @Override
            public void onFailure(Call<BaseResponse<FeedArticleListData>> call, Throwable t) {
                Log.e("-------", "失败:"+t.getMessage());
                callback.onLoadCollectionListError();
            }
        });
    }
}
