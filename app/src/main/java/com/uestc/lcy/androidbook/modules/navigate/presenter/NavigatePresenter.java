package com.uestc.lcy.androidbook.modules.navigate.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.NavigateBean;
import com.uestc.lcy.androidbook.modules.navigate.NavigateActivity;
import com.uestc.lcy.androidbook.modules.navigate.callback.NavigateCallback;
import com.uestc.lcy.androidbook.modules.navigate.model.NavigateModel;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public class NavigatePresenter extends BasePresenter<NavigateActivity> implements NavigateCallback{

    private NavigateModel mModel;

    public NavigatePresenter() {
        mModel = new NavigateModel();
    }

    public void loadNavigation() {
        mView.showLoading();
        mModel.loadNavigation(this);
    }

    @Override
    public void onLoadNavigationSuccess(NavigateBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadNavigationSuccess(bean);
        }
    }

    @Override
    public void onLoadNavigationError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadNavigationError();
        }
    }
}
