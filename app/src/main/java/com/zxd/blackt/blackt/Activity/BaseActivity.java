package com.zxd.blackt.blackt.Activity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zxd.blackt.blackt.Utils.EventBusUtils.Event;
import com.zxd.blackt.blackt.Utils.EventBusUtils.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by zhuangxd on 2017/5/4.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        /**
         * 没有状态栏的浸透式
         * 调用getWindow().getDecorView()方法获取到了当前界面的DecorView，
         * 然后调用它的setSystemUiVisibility()方法来设置系统UI元素的可见性。
         * 其中，SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏。
         * 另外，根据Android的设计建议，ActionBar是不应该独立于状态栏而单独显示的，
         * 因此状态栏如果隐藏了，我们同时也需要调用ActionBar的hide()方法将ActionBar也进行隐藏。
         */
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        /**
         * 有状态栏的浸透式，5.0以上，需判断
         * 使用了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN和SYSTEM_UI_FLAG_LAYOUT_STABLE，
         * 注意两个Flag必须要结合在一起使用，
         * 表示会让应用的主体内容占用系统状态栏的空间，
         * 最后再调用Window的setStatusBarColor()方法将状态栏设置成透明色就可以了
         */
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
    }

    /**
     * 真正的沉浸式模式。
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    /**
     * 更名为：随意亦人生
     * 在欢迎页的时候就请求每日一句，将结果带到首页。
     * 首页改成每日一句，每日一句有由左至右一字一字出现的动画，底部中间一个打开是扇形的悬浮按钮，按钮包括新闻、日记、音乐、设置。
     * 新闻不变。
     * 音乐不变。
     */

}
