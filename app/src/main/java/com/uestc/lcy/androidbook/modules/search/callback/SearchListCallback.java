package com.uestc.lcy.androidbook.modules.search.callback;

import com.uestc.lcy.androidbook.model.HotKeyBean;
import com.uestc.lcy.androidbook.model.SearchBean;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public interface SearchListCallback {
    void onLoadSearchListSuccess(SearchBean bean);
    void onLoadSearchListError();
    void onLoadMoreSearchListSuccess(SearchBean bean);
    void onLoadHotKeySuccess(HotKeyBean bean);
    void onLoadHotKeyError();
}
