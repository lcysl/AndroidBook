package com.uestc.lcy.androidbook.modules.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.modules.MainActivity;

/**
 * Created by lcy on 2018\4\9 0009.
 */

public class GuideContentFragment extends Fragment {
    private int[] bgRes = new int[]
            {
                    R.drawable.guidepage1,
                    R.drawable.guidepage2,
                    R.drawable.guidepage3
            };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_content, null);
        Button btnGuide = view.findViewById(R.id.btn_guide);

        //动态给RelativeLayout设置背景
        RelativeLayout rlGuide = view.findViewById(R.id.rl_guide);
        //获得当前是第几张图片，由activity传过来的
        int index = getArguments().getInt("index");
        rlGuide.setBackgroundResource(bgRes[index]);

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
        //设置按钮的可见性，最后一章图片才显示
        btnGuide.setVisibility(index == 2 ? View.VISIBLE : View.GONE);
        return view;
    }
}
