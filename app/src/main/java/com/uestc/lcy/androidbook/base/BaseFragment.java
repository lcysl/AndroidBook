package com.uestc.lcy.androidbook.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.lcy.androidbook.component.LoadingDialogFragment;

/**
 * Created by lcy on 2018\4\18 0018.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment{
    protected T mPresenter;

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected abstract void initPresenter();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initPresenter();
        View view = createView(inflater, container, savedInstanceState);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 显示加载进度条（利用对话框）
     */
    public void showLoadingDialog() {
        LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
        loadingDialogFragment.show(getActivity().getFragmentManager(), "LoadingDialog");
    }

    /**
     * 隐藏加载进度条
     */
    public void hideLoadingDialog() {
        LoadingDialogFragment loadingDialogFragment =
                (LoadingDialogFragment) getActivity().getFragmentManager().findFragmentByTag("LoadingDialog");
        if (loadingDialogFragment != null) {
            loadingDialogFragment.dismiss();
        }
    }
}
