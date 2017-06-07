package com.zxd.blackt.blackt.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhuangxd on 2017/5/4.
 */

public class App extends Application{

    private static Context  context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
