package com.zxd.blackt.blackt.Customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by zhuangxd on 2017/8/1.
 */

public class BTListView extends ListView {
    public BTListView(Context context) {
        super(context);
    }

    public BTListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BTListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
