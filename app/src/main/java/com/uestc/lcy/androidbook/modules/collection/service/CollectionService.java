package com.uestc.lcy.androidbook.modules.collection.service;

import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.model.BaseResponse;
import com.uestc.lcy.androidbook.model.FeedArticleListData;
import com.uestc.lcy.androidbook.model.LoginBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 收藏service
 * Created by lcy on 2018\6\16 0016.
 */

public interface CollectionService {

    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Call<Void> collection(@Field("title") String title, @Field("author") String author, @Field("link") String link);

    @POST("lg/uncollect_originId/{id}/json")
    Call<Void> cancelCollection(@Path("id") int id);

    @GET("lg/collect/list/{page}/json")
    Call<BaseResponse<FeedArticleListData>> loadCollectionList(@Path("page") int page);


}
