package com.uestc.lcy.androidbook.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.uestc.lcy.androidbook.constant.App;

/**
 * App 配置信息
 * Created by lcy on 2018\4\9 0009.
 */

public class AppConfig {
    //饿汉式单例模式(3个条件)

    //静态私有成员变量
    private static AppConfig instance = new AppConfig();
    //私有构造函数
    private AppConfig() {}
    //静态共有工厂方法，返回唯一实例
    public static AppConfig getInstance() {
        return instance;
    }

    private static SharedPreferences sharedPreferences = null;

    //在Application初始化
    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(App.APP_CONFIG, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor editor;

    /**
     * set方法
     */
    public void setBoolean(String key,boolean value) {
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    /**
     * get方法
     */
    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }
}
