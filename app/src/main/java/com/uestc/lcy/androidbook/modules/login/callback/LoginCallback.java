package com.uestc.lcy.androidbook.modules.login.callback;

import com.uestc.lcy.androidbook.model.LoginBean;

/**
 * Created by Administrator on 2018\4\16 0016.
 */

public interface LoginCallback {
    void onLoginSuccess(LoginBean bean);
    void onLoginError();
}
