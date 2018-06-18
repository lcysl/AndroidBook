package com.uestc.lcy.androidbook.modules.collection.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.FeedArticleData;
import com.uestc.lcy.androidbook.modules.collection.CollectionActivity;
import com.uestc.lcy.androidbook.modules.collection.callback.CollectionCallback;
import com.uestc.lcy.androidbook.modules.collection.callback.CollectionListCallback;
import com.uestc.lcy.androidbook.modules.collection.model.CollectionModel;

import java.util.List;

/**
 * 收藏Presenter
 * Created by lcy on 2018\6\16 0016.
 */

public class CollectionPresenter extends BasePresenter<CollectionActivity> implements CollectionListCallback {

    private CollectionModel mModel;
    public CollectionPresenter() {
        mModel = new CollectionModel();
    }

    public void loadCollectionList(int page) {
        mModel.loadCollectionList(page, this);
    }

    @Override
    public void onLoadCollectionListSuccess(List<FeedArticleData> collectionList) {
        if(mView != null) {
            mView.onLoadCollectionListSuccess(collectionList);
        }
    }

    @Override
    public void onLoadCollectionListError() {
        if(mView != null) {
            mView.onLoadCollectionListError();
        }
    }
}
