package com.uestc.lcy.androidbook.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.uestc.lcy.androidbook.constant.App;

/**
 * Created by lcy on 2018\4\18 0018.
 */

public class CookieConfig {
    //饿汉式单例模式(3个条件)

    //静态私有成员变量
    private static CookieConfig instance = new CookieConfig();
    //私有构造函数
    private CookieConfig() {}
    //静态共有工厂方法，返回唯一实例
    public static CookieConfig getInstance() {
        return instance;
    }

    private static SharedPreferences sharedPreferences = null;

    //在Application初始化
    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(App.COOKIE_CONFIG, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor editor;

    /**
     * set方法
     */
    public void setString(String key,String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * get方法
     */
    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
