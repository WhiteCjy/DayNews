package com.group.daynews.appilicatons;

import android.app.Application;

import com.group.daynews.BuildConfig;

import org.xutils.x;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);


    }
}
