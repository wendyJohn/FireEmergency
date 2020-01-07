package com.sanleng.sl.fireemergency.mvp.ui;

import android.app.Activity;
import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    private static MyApplication sAppContext;
    private static MyApplication instance;
    private List<Activity> activityList = new LinkedList<Activity>();
    private static final String TAG = "MyApplication";
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 0x1231232;
    public static final int MESSAGE_PROGRESS = 0x32654599;
    public static final int MESSAGE_UPDATE = 0x12654599;
    public static final int MESSAGE_UPDATEOK = 0x22654599;

    public static final int MESSAGE_UPLOADSUCCESS = 0x1256222;
    public static final int MESSAGE_UPLOADFAIL = 0x1256333;

    // 单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        initOkhttp();
    }


    public static MyApplication getAppContext() {
        return sAppContext;
    }

    private void initOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    // 遍历所有Activity并finish
    public void exitNoMain() {
        for (Activity activity : activityList) {
            if (activity instanceof MainActivity) {

            } else {
                activity.finish();
            }
        }
    }
    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 添加Activity到容器中
    public void clearActivity() {
        activityList.clear();
    }

}
