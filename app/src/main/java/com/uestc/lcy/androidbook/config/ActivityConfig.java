package com.uestc.lcy.androidbook.config;


import java.util.ArrayList;
import java.util.List;

/**
 * 实现存储当前Activity,获取当前Activity，获取上一个Activity，删除当前Activity
 * Created by lcy on 2018\4\17 0017.
 */

public class ActivityConfig {
    //静态私有成员变量
    private static ActivityConfig instance = new ActivityConfig();
    //私有构造函数
    private ActivityConfig() {}
    //静态共有工厂方法，返回唯一实例
    public static ActivityConfig getInstance() {
        return instance;
    }

    private List<String> list = new ArrayList<>();

    /**
     * 存储当前Activity
     * @param activity
     */
    public void pushCurrentActivity(Class activity) {
        list.add(activity.getName());
    }

    /**
     * 返回当前Activity
     * @return
     */
    public String getCurrentActivity() {
        return list.get(list.size() - 1);
    }

    /**
     * 获取上一个Activity
     * @return
     */
    public String getLastActivity() {
        return list.get(list.size() - 2);
    }

    /**
     * 删除当前Activity
     */
    public void delete() {
        list.remove(list.size() - 1);
    }
}
