package com.zxd.blackt.blackt.Common.Events;

/**
 * Created by zhuangxd on 2017/9/13.
 */

public class ExitEvent {

    private int isLast;

    public ExitEvent(int isLast) {
        this.isLast = isLast;
    }

    public int getIsLast() {
        return isLast;
    }
}
