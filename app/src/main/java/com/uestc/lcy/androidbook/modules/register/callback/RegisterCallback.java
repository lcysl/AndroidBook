package com.uestc.lcy.androidbook.modules.register.callback;

import com.uestc.lcy.androidbook.model.RegisterBean;

/**
 * 注册 回调接口， 从P层传到M层，M层处理完成后，用此接口回调P层的方法
 * Created by lcy on 2018\4\14 0014.
 */

public interface RegisterCallback {

    void onRegisterSuccess(RegisterBean bean);
    void onRegisterError();
}
