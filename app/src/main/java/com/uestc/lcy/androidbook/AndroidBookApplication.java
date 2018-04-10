package com.uestc.lcy.androidbook;

import android.app.Application;

import com.uestc.lcy.androidbook.config.AppConfig;
import com.uestc.lcy.androidbook.modules.guide.GuideActivity;

/**
 * Created by lcy on 2018\4\8 0008.
 */

public class AndroidBookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化AppConfig的sp
        AppConfig.init(this);
    }
}
