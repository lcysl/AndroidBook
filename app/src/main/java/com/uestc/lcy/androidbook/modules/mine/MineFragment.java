package com.uestc.lcy.androidbook.modules.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.modules.login.LoginActivity;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class MineFragment extends Fragment {

    private LinearLayout mLlCollect;
    private LinearLayout mLlBookMark;
    private LinearLayout mLlSetting;

    private ImageView ivCollectIcon;
    private TextView tvCollectTitle;

    private ImageView ivBookMarkIcon;
    private TextView tvBookMarkTitle;

    private ImageView ivSettingIcon;
    private TextView tvSettingTitle;

    private ImageView mAvatar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        initLayout(view);
        initView(view);
        setListener();

        return view;
    }




    /**
     * 初始化每一项（3项）的布局
     * @param view
     */
    private void initLayout(View view) {
        mLlCollect = view.findViewById(R.id.mine_collect);
        mLlBookMark = view.findViewById(R.id.mine_book_mark);
        mLlSetting  = view.findViewById(R.id.mine_setting);
    }

    /**
     * 初始化每一项的子控件（图片和标题）
     */
    private void initView(View view) {
        mAvatar = view.findViewById(R.id.iv_avatar);

        ivCollectIcon = mLlCollect.findViewById(R.id.iv_mine_list_icon);
        tvCollectTitle = mLlCollect.findViewById(R.id.tv_mine_list_title);

        ivBookMarkIcon = mLlBookMark.findViewById(R.id.iv_mine_list_icon);
        tvBookMarkTitle = mLlBookMark.findViewById(R.id.tv_mine_list_title);

        ivSettingIcon = mLlSetting.findViewById(R.id.iv_mine_list_icon);
        tvSettingTitle = mLlSetting.findViewById(R.id.tv_mine_list_title);

        ivCollectIcon.setImageResource(R.drawable.mine_collect);
        tvCollectTitle.setText(R.string.mine_collect);

        ivBookMarkIcon.setImageResource(R.drawable.mine_book_mark);
        tvBookMarkTitle.setText(R.string.mine_book_mark);

        ivSettingIcon.setImageResource(R.drawable.mine_setting);
        tvSettingTitle.setText(R.string.mine_setting);
    }

    private void setListener() {
        //点击头像显示登录界面
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }
}
