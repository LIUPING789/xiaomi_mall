package com.knight.xiaomimall.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * description: ${TODO}
 * autour: Knight
 * new date: 2018/9/13 on 15:09
 * e-mail: 37442216knight@gmail.com
 * update: 2018/9/13 on 15:09
 * version: v 1.0
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
