package com.uestc.lcy.androidbook.modules.search.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.HotKeyBean;
import com.uestc.lcy.androidbook.model.SearchBean;
import com.uestc.lcy.androidbook.modules.search.callback.SearchListCallback;
import com.uestc.lcy.androidbook.modules.search.model.SearchListModel;
import com.uestc.lcy.androidbook.modules.search.view.SearchListView;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public class SearchListPresenter extends BasePresenter<SearchListView> implements SearchListCallback{

    private SearchListModel mModel;

    public SearchListPresenter() {
        mModel = new SearchListModel();
    }

    public void loadSearchList(int page, String key) {
        mView.showLoading();
        mModel.loadSearchList(page, key, this);
    }

    public void loadHotKey() {
        mView.showLoading();
        mModel.loadHotKey(this);
    }

    @Override
    public void onLoadSearchListSuccess(SearchBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadSearchListSuccess(bean);
        }
    }

    @Override
    public void onLoadSearchListError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadSearchListError();
        }
    }

    @Override
    public void onLoadMoreSearchListSuccess(SearchBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadMoreSearchListSuccess(bean);
        }
    }

    @Override
    public void onLoadHotKeySuccess(HotKeyBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadHotKeySuccess(bean);
        }
    }

    @Override
    public void onLoadHotKeyError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoadHotKeyError();
        }
    }
}
