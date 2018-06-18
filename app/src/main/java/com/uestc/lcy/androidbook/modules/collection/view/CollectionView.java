package com.uestc.lcy.androidbook.modules.collection.view;

import com.uestc.lcy.androidbook.base.BaseView;

/**
 * 收藏的View
 * Created by lcy on 2018\4\16 0016.
 */

public interface CollectionView extends BaseView {

    void onCollectionSuccess();
    void onCollecionError();

    void onCancelCollectionSuccess();
    void onCancelCollecionError();

}
