package com.uestc.lcy.androidbook.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uestc.lcy.androidbook.constant.App;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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

    /**
     *set一个集合类型
     */
    public <T> void setList(String key, List<T> value) {
        editor = sharedPreferences.edit();
        if (value == null || value.size() <= 0) {
            return;
        }
        Gson gson = new Gson();
        String strJson = gson.toJson(value);
        editor.putString(key, strJson);
        editor.commit();
    }
    /**
     * get一个集合类型
     */
    public <T> List<T> getList(String key) {
        List<T> dataList = new ArrayList<>();
        String strJson = sharedPreferences.getString(key, null);
        if (strJson == null) {
            return dataList;
        }
        Gson gson = new Gson();
        dataList = gson.fromJson(strJson, new TypeToken<List<T>>(){}.getType());
        return dataList;
    }
}
