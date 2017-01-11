package com.zhuoxin.hunttreasure;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.zhuoxin.hunttreasure.user.UserPrefs;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UserPrefs.init(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
    }
}