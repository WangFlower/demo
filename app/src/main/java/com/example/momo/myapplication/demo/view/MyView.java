package com.example.momo.myapplication.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MOMO on 16/11/28.
 */

public class MyView extends View {


    private Path path;

    private Paint paintS;
    //圆心坐标
    private int centerX = 400;
    private int centerY = 400;
    //半径
    private int r = 300;
    //大刻度
    private int bs = r / 6;
    private int ss = bs / 4;

    private boolean isInit = false;

    public MyView(Context context) {
        super(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        paintS = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintS.setStyle(Paint.Style.STROKE);
        paintS.setStrokeWidth(3);
        paintS.setTextSize(24);
        paintS.setColor(Color.BLUE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!this.isInit) {
            Path path = new Path();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(6);
            paint.setColor(Color.BLUE);
            path.addCircle(centerX, centerY, r, Path.Direction.CW);
            canvas.drawPath(path, paint);
            paint.setColor(Color.RED);

            canvas.save();
            int tempS;
            String textS;
            for (int i = 0; i < 60; i++) {
                if (i % 5 == 0) {
                    tempS = bs;
                    if (i / 5 == 0) {
                        textS = String.valueOf(12);
                    } else {
                        textS = String.valueOf(i / 5);
                    }
                    canvas.drawText(textS, centerX, centerX - r + tempS + 4, paintS);
                } else {
                    tempS = ss;
                }
                canvas.drawLine(centerX, centerX - r, centerX, (centerY - r) + tempS, paint);
                canvas.rotate(6, centerX, centerX);
            }
            canvas.restore();
        }


    }


}
