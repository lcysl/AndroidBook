package com.uestc.lcy.androidbook.modules.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseFragment;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class HomeFragment extends BaseFragment {
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void initPresenter() {

    }



}
