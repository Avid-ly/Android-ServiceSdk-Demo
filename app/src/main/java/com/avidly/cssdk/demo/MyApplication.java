package com.avidly.cssdk.demo;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
