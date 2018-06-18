package com.uestc.lcy.androidbook.modules.collection.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.FeedArticleData;

import java.util.List;

/**
 * Created by Administrator on 2018\6\16 0016.
 */

public interface CollectionListView extends BaseView {

    void onLoadCollectionListSuccess(List<FeedArticleData> collectionList);
    void onLoadCollectionListError();
}
