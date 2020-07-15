package com.example.aiyang.stickydecoration.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 圆角矩形，裁剪后子view内容不外延
 */
public class RoundRectLinearLayout extends LinearLayout {
    public RoundRectLinearLayout(Context context) {
        super(context);
    }

    public RoundRectLinearLayout(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundRectLinearLayout(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    /**
     * onDraw()先于dispatchDraw()执行,用于本身控件的绘制,dispatchDraw()用于子控件的绘制
     * onDraw()绘制的内容可能会被子控件覆盖而dispatchDraw()是子控件的绘制,所以是覆盖在onDraw()上的
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {

        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), 60, 60, Path.Direction.CW);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            canvas.clipPath(path);
        }else {
            canvas.clipPath(path, Region.Op.REPLACE);
        }
        super.dispatchDraw(canvas);
    }

}
