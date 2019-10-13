package com.emdd.friendscircle;

import android.app.Application;
import android.content.Context;

import com.emdd.friendscircle.others.DataCenter;


/**
 * @author KCrason
 * @date 2018/5/3
 */
public class BaseApplication extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        DataCenter.init();
    }
}
