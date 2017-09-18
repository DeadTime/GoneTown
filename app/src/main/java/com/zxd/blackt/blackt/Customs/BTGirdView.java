package com.zxd.blackt.blackt.Customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by zhuangxd on 2017/8/1.
 */

public class BTGirdView extends GridView {


    public BTGirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public BTGirdView(Context context) {
        super(context);
    }


    public BTGirdView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
