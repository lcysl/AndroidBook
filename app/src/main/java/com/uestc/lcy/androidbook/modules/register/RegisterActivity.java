package com.uestc.lcy.androidbook.modules.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.model.RegisterBean;
import com.uestc.lcy.androidbook.modules.login.LoginActivity;
import com.uestc.lcy.androidbook.modules.register.presenter.RegisterPresenter;
import com.uestc.lcy.androidbook.modules.register.view.RegisterView;

/**
 * 注册界面
 * Created by lcy on 2018\4\13 0013.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView , View.OnClickListener {

    private Button mBackBtn;
    private TextView mRegisterTopBarTitle;
    private LinearLayout mRegisterTopBarLayout;
    private Button mRegisterBtn;
    private EditText mUserNameEt, mPasswordEt, mRepasswordEt;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new RegisterPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void initView() {
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
        mRegisterTopBarLayout = findViewById(R.id.top_bar_register);
        mRegisterTopBarTitle = mRegisterTopBarLayout.findViewById(R.id.tv_top_bar2_title);

        mRegisterTopBarTitle.setText(R.string.register);
        mRegisterBtn = findViewById(R.id.btn_register);
        mUserNameEt = findViewById(R.id.et_reg_username);
        mPasswordEt = findViewById(R.id.et_reg_password);
        mRepasswordEt = findViewById(R.id.et_reg_repassword);
    }

    private void initListener() {
        mBackBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_top_bar2_return:
                onBackPressed();
                break;
            case R.id.btn_register:
                //点击注册按钮，把从面板中获得的数据传到P层
                //调用P层的register方法
                String username = mUserNameEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                String repassword = mRepasswordEt.getText().toString();
                mPresenter.register(username, password, repassword);
                break;
            default:
                break;
        }
    }

    /**
     * 将网络请求成功后返回的数据更新面板的相关位置
     * @param bean
     */
    @Override
    public void onRegisterSuccess(RegisterBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            Bundle bundle = new Bundle();
            bundle.putString("username", bean.getData().getUsername());
            bundle.putString("password", bean.getData().getPassword());
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    /**
     * 网络请求失败打印吐司
     */
    @Override
    public void onRegisterError() {
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
