package com.uestc.lcy.androidbook.modules.collection;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.lcy.androidbook.R;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class CollectFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        return view;
    }
}
