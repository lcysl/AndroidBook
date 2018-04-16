package com.uestc.lcy.androidbook.modules.login.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.LoginBean;
import com.uestc.lcy.androidbook.modules.login.LoginActivity;
import com.uestc.lcy.androidbook.modules.login.callback.LoginCallback;
import com.uestc.lcy.androidbook.modules.login.model.LoginModel;

/**
 * Created by Administrator on 2018\4\16 0016.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginCallback {

    private LoginModel mModel;

    public LoginPresenter() {
        mModel = new LoginModel();
    }

    public void login(String username, String password) {
        mView.showLoading();
        mModel.login(username, password, this);
    }

    @Override
    public void onLoginSuccess(LoginBean bean) {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoginSuccess(bean);
        }
    }

    @Override
    public void onLoginError() {
        if (mView != null) {
            mView.hideLoading();
            mView.onLoginError();
        }
    }
}
