package com.zxd.blackt.blackt.Util.EventBusUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zhuangxd on 2017/9/13.
 */

public class EventBusUtil {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

}
