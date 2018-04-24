package com.uestc.lcy.androidbook.modules.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseFragment;
import com.uestc.lcy.androidbook.config.UserConfig;
import com.uestc.lcy.androidbook.model.LoginBean;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.login.LoginActivity;
import com.uestc.lcy.androidbook.modules.userinfo.UserInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class MineFragment extends BaseFragment {

    private MainActivity mActivity;

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
    private TextView mUsernameTv;

    private Button mUnLoginBtn;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initLayout(view);
        initView(view);
        initData();
        setListener();
        return view;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
        mUsernameTv = view.findViewById(R.id.tv_username);

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

        mUnLoginBtn = view.findViewById(R.id.btn_unlogin);
    }


    private void initData() {
        String userInfo = UserConfig.getInstance().getString("userInfo", null);
        if (userInfo != null) {
            Gson gson = new Gson();
            LoginBean.DataBean bean = gson.fromJson(userInfo, LoginBean.DataBean.class);
            mUsernameTv.setText(bean.getUsername());
        }
    }



    private void setListener() {
        //点击头像判断是否保存了用户信息，如果用户信息不为空，则弹出用户信息界面
        //如果用户信息为空，则弹出登录界面
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInfo = UserConfig.getInstance().getString("userInfo", null);
//                Log.d("-----MineFragment------", userInfo+"");
                if (userInfo != null) {
                    //弹出用户信息界面
                    Intent intent = new Intent(mActivity, UserInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userInfo", userInfo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        //点击退出登录按钮，清空用户数据
        mUnLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserConfig.getInstance().clear();
                mUsernameTv.setText("未登录");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String string) {
        mUsernameTv.setText(string);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
