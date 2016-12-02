package com.example.momo.myapplication.demo.canvas;

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

public class PathView extends View {


    private Path path;
    private Paint paint;

    public PathView(Context context) {
        super(context,null);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        path.addRect(100,100,200,200, Path.Direction.CW);
//        path.setLastPoint(50,10);
//        path.moveTo(100, 100);
//        RectF oval = new RectF(100, 100, 300, 400);
//        path.addOval(oval, Path.Direction.CW);
//
//        canvas.drawPath(path, paint);
//        paint.setColor(Color.RED);
//        paint.setTextSize(50);
//        canvas.drawTextOnPath("123213123123123123123",path,00,0,paint);

        Path path1 = new Path();
        path1.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(200, 200, 100, Path.Direction.CW);
    }


}
