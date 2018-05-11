package com.uestc.lcy.androidbook.modules.user_info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.model.LoginBean;

/**
 * Created by lcy on 2018\4\23 0023.
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener{

    private Button mBackBtn;
    private TextView mTopBarTitle;

    private TextView mUserNameTt;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        setListener();
    }

    @Override
    protected void initPresenter() {

    }

    private void initView() {
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
        mTopBarTitle = findViewById(R.id.tv_top_bar2_title);

        mUserNameTt = findViewById(R.id.tv_username);
    }

    private void initData() {
        mTopBarTitle.setText("我的信息");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userInfo = bundle.getString("userInfo");
        Gson gson = new Gson();
        LoginBean.DataBean bean = gson.fromJson(userInfo, LoginBean.DataBean.class);
        mUserNameTt.setText(bean.getUsername());
    }


    private void setListener() {
        mBackBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_top_bar2_return:
                onBackPressed();
                break;
        }
    }
}
