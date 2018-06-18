package com.uestc.lcy.androidbook.modules.collection.callback;

import com.uestc.lcy.androidbook.model.FeedArticleData;

import java.util.List;

/**
 * 收藏模块回调
 * Created by Administrator on 2018\6\16 0016.
 */

public interface CollectionListCallback {

    void onLoadCollectionListSuccess(List<FeedArticleData> collectionList);
    void onLoadCollectionListError();
}
