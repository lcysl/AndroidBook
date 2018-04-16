package com.uestc.lcy.androidbook.modules.login.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.LoginBean;

/**
 * Login的View
 * Created by lcy on 2018\4\16 0016.
 */

public interface LoginView extends BaseView {
    void onLoginSuccess(LoginBean bean);
    void onLoginError();
}
