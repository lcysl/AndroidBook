package com.uestc.lcy.androidbook.modules.register.presenter;

import com.uestc.lcy.androidbook.base.BasePresenter;
import com.uestc.lcy.androidbook.model.RegisterBean;
import com.uestc.lcy.androidbook.modules.register.RegisterActivity;
import com.uestc.lcy.androidbook.modules.register.callback.RegisterCallback;
import com.uestc.lcy.androidbook.modules.register.model.RegisterModel;

/**
 * 注册P层
 * Created by lcy on 2018\4\14 0014.
 */

public class RegisterPresenter extends BasePresenter<RegisterActivity> implements RegisterCallback{

    private RegisterModel mModel;

    public RegisterPresenter() {
        mModel = new RegisterModel();
    }

    public void register(String username, String password, String repassword) {
        mModel.register(username, password, repassword, this);
    }

    /**
     * 调用View层的方法
     * @param bean
     */
    @Override
    public void onRegisterSuccess(RegisterBean bean) {
        if (mView != null) {
            mView.onRegisterSuccess(bean);
        }
    }

    @Override
    public void onRegisterError() {
        if (mView != null) {
            mView.onRegisterError();
        }
    }
}
