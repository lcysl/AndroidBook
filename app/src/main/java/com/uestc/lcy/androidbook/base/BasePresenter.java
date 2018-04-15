package com.uestc.lcy.androidbook.base;

/**
 * Created by lcy on 2018\4\14 0014.
 */

public class BasePresenter<T extends BaseView> implements IPresenter<T> {
    protected T mView;
    /**
     * 把view传到P层
     * @param view
     */
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
