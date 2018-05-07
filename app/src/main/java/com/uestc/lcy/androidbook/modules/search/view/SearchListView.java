package com.uestc.lcy.androidbook.modules.search.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.SearchBean;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public interface SearchListView extends BaseView{
    void onLoadSearchListSuccess(SearchBean bean);
    void onLoadSearchListError();
    void onLoadMoreSearchListSuccess(SearchBean bean);
}
