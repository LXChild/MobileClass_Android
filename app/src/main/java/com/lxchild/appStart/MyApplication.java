package com.lxchild.appStart;

import android.app.Activity;
import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import java.util.Stack;

/**
 * Created by LXChild on 16/10/2016.
 */

public class MyApplication extends Application {

    private static Stack<Activity> activityStack;
    private static MyApplication singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        InitializeInMainThread();
        InitializeService.start(this);
    }

    private void InitializeInMainThread() {
        // 初始化LeanCloud API
        AVOSCloud.initialize(this, "YebDfOq0GeDFvHIQqtVoHIKh-gzGzoHsz", "WYlONH9eCNC989qfWp9db5On");
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
    }

    // Returns the application instance
    public static MyApplication getInstance() {
        return singleton;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    private void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
