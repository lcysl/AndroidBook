package com.uestc.lcy.androidbook.modules.navigate.callback;

import com.uestc.lcy.androidbook.model.NavigateBean;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public interface NavigateCallback {
    void onLoadNavigationSuccess(NavigateBean bean);
    void onLoadNavigationError();
}
