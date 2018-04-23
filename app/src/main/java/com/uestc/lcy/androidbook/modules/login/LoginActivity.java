package com.uestc.lcy.androidbook.modules.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.config.ActivityConfig;
import com.uestc.lcy.androidbook.model.LoginBean;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.login.presenter.LoginPresenter;
import com.uestc.lcy.androidbook.modules.login.view.LoginView;
import com.uestc.lcy.androidbook.modules.register.RegisterActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * 登录界面
 * Created by lcy on 2018\4\12 0012.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView, View.OnClickListener{

    private Button mBackBtn;
    private TextView mToRegisterTv;
    private Button mLoginBtn;
    private TextInputEditText mUsernameEt, mPasswordEt;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initListener();
        initData();
    }


    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void initView() {
        mToRegisterTv = findViewById(R.id.tv_to_register);
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
        mLoginBtn = findViewById(R.id.btn_login);
        mUsernameEt = findViewById(R.id.et_username);
        mPasswordEt = findViewById(R.id.et_password);
    }

    private void initListener() {
        mToRegisterTv.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
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
            case R.id.btn_login:
                String username = mUsernameEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                mPresenter.login(username, password);
                break;
            default:
                break;
        }
    }

    /**
     * 从注册界面获得数据，并自动登录
     */
    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String username = bundle.getString("username");
            String password = bundle.getString("password");
            mUsernameEt.setText(username);
            mPasswordEt.setText(password);
            mLoginBtn.performClick();
        }
    }

    /**
     * 网络请求成功后更新面板相关位置
     * @param bean
     */
    @Override
    public void onLoginSuccess(LoginBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            //通知我的界面刷新数据
            EventBus.getDefault().post(bean.getData().getUsername());
            if (ActivityConfig.getInstance().getLastActivity().equals(MainActivity.class.getName())) {
                onBackPressed();
            } else {
                //从注册跳转过来的
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onLoginError() {
        Toast.makeText(this, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        super.showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        super.hideLoadingDialog();
    }
}
