package com.architecturedemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全局application
 *
 * @author weishixiong
 * @Time 2018-03-16
 */
public class CcsApplication extends MultiDexApplication {
    private static final String TAG = "CcsApplication";
    private static CcsApplication instance;

    /**
     * 存放所有的activity
     */
    public static List<Activity> runActivities = new ArrayList<>();
    /**
     * 上下文
     */
    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        context = getApplicationContext();

    }

    //如果应用屏幕固定了某个方向不旋转的话(比如qq和微信),下面可不写.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }



    /**
     * 添加一个正在运行的Activity进入容器
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        runActivities.add(activity);
    }

    /**
     * 移除被销毁的Activiiy
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        runActivities.remove(activity);

    }

    public static CcsApplication getInstance() {
        return instance;
    }

    /**
     * 释放所有正在运行的Activity
     */
    public static void clearAllActivitys() {
        for (int i = 0; i < runActivities.size(); i++) {
            runActivities.get(i).finish();
        }
        runActivities.clear();
    }

    /**
     * 释放指定的activity
     *
     * @param exclude 要finish的Activity
     */
    public static void clearSpecifyActivities(Class<Activity> exclude[]) {
        for (int i = 0; i < runActivities.size(); i++) {
            Activity a = runActivities.get(i);
            if (a != null && Arrays.asList(exclude).contains(a.getClass())) {
                a.finish();
                runActivities.remove(a);
            }
        }

    }



}