package com.uestc.lcy.androidbook.modules.register.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.RegisterBean;

/**
 * Created by lcy on 2018\4\14 0014.
 */

public interface RegisterView extends BaseView{
    void onRegisterSuccess(RegisterBean bean);
    void onRegisterError();
}
