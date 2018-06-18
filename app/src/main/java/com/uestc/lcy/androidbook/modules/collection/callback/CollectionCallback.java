package com.uestc.lcy.androidbook.modules.collection.callback;

/**
 * 收藏模块回调
 * Created by Administrator on 2018\6\16 0016.
 */

public interface CollectionCallback {
    void onCollectionSuccess();
    void onCollectionError();
    void onCancelCollectionSuccess();
    void onCancelCollectionError();
}
