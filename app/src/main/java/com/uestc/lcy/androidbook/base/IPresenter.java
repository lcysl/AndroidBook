package com.uestc.lcy.androidbook.base;

/**
 * Created by lcy on 2018\4\14 0014.
 */

public interface IPresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
