package com.uestc.lcy.androidbook.modules.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.modules.register.RegisterActivity;

/**
 * 登录界面
 * Created by lcy on 2018\4\12 0012.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private Button mBackBtn;
    private TextView mToRegisterTv;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initListener();
    }

    @Override
    protected void initPresenter() {

    }


    private void initView() {
        mToRegisterTv = findViewById(R.id.tv_to_register);
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
    }

    private void initListener() {
        mToRegisterTv.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_top_bar2_return:
                onBackPressed();
                break;
            case R.id.tv_to_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }
}
