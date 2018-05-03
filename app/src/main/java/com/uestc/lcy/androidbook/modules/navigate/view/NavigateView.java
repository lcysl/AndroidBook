package com.uestc.lcy.androidbook.modules.navigate.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.NavigateBean;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public interface NavigateView extends BaseView {
    void onLoadNavigationSuccess(NavigateBean bean);
    void onLoadNavigationError();
}
