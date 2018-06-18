package com.uestc.lcy.androidbook;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.uestc.lcy.androidbook.config.ActivityConfig;
import com.uestc.lcy.androidbook.config.AppConfig;
import com.uestc.lcy.androidbook.config.CookieConfig;
import com.uestc.lcy.androidbook.config.UserConfig;
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
        //初始化UserConfig的sp
        UserConfig.init(this);
        CookieConfig.init(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ActivityConfig.getInstance().pushCurrentActivity(activity.getClass());
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //ActivityConfig.getInstance().delete();
            }
        });
    }
}
