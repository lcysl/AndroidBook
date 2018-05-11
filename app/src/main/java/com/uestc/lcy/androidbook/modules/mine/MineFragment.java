package com.uestc.lcy.androidbook.modules.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.uestc.lcy.androidbook.modules.user_info.UserInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class MineFragment extends BaseFragment {

    private MainActivity mActivity;
    /*收藏、书签和设置*/
    private LinearLayout mLlCollect;
    private LinearLayout mLlBookMark;
    private LinearLayout mLlSetting;
    private ImageView mCollectIconIv;
    private TextView mCollectTitleTv;
    private ImageView mBookMarkIconIv;
    private TextView mBookMarkTitleTv;
    private ImageView mSettingIconIv;
    private TextView mSettingTitleTv;
    /*头像和用户名*/
    private ImageView mAvatar;
    private TextView mUsernameTv;
    /*取消登录*/
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
        //注册事件，用于接收从登录界面传过来的信息
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

        mCollectIconIv = mLlCollect.findViewById(R.id.iv_mine_list_icon);
        mCollectTitleTv = mLlCollect.findViewById(R.id.tv_mine_list_title);

        mBookMarkIconIv = mLlBookMark.findViewById(R.id.iv_mine_list_icon);
        mBookMarkTitleTv = mLlBookMark.findViewById(R.id.tv_mine_list_title);

        mSettingIconIv = mLlSetting.findViewById(R.id.iv_mine_list_icon);
        mSettingTitleTv = mLlSetting.findViewById(R.id.tv_mine_list_title);

        mCollectIconIv.setImageResource(R.drawable.mine_collect);
        mCollectTitleTv.setText(R.string.mine_collect);

        mBookMarkIconIv.setImageResource(R.drawable.mine_book_mark);
        mBookMarkTitleTv.setText(R.string.mine_book_mark);

        mSettingIconIv.setImageResource(R.drawable.mine_setting);
        mSettingTitleTv.setText(R.string.mine_setting);

        mUnLoginBtn = view.findViewById(R.id.btn_unlogin);
    }


    private void initData() {
        //从sp中取出用户的信息，用于更新面板
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
