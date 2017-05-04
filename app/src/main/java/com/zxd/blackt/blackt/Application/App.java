package com.zxd.blackt.blackt.Application;

import android.app.Application;
import android.content.Context;
import android.widget.PopupWindow;

/**
 * Created by zhuangxd on 2017/5/4.
 */

public class App extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getcontext() {
        return context;
    }
}
