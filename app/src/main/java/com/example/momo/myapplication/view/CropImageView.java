package com.example.momo.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.momo.myapplication.utils.UIUtils;

/**
 * Created by wang.renguang on 2018/5/7.
 */

public class CropImageView extends ImageView {

    private static final int DEFAULT_VIEW_WIDTH = UIUtils.getPixels(57);
    private static final int DEFAULT_VIEW_HEIGHT = UIUtils.getPixels(57);


    private int innerR =  UIUtils.getPixels(4.5f);

    private int innerL = UIUtils.getPixels(1);
    private int outL = UIUtils.getPixels(1.5f);
    private Path path = new Path();
    private Paint paint;

    private int mWidth;
    private int mHeight;

    public CropImageView(Context context) {
        super(context);
        init();
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CropImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(DEFAULT_VIEW_WIDTH, widthMeasureSpec);
        int height = measureDimension(DEFAULT_VIEW_HEIGHT, heightMeasureSpec);
        mWidth = Math.min(width,height);
        mHeight = mWidth;
        setMeasuredDimension(width, height);
    }

    protected int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = specSize;
                break;
            default:
                result = defaultSize;
                break;

        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // 裁切画布
        path.moveTo(mWidth/2,mWidth/2);
        path.addCircle(mWidth/2,mWidth/2,innerR,Path.Direction.CW);
        canvas.clipPath(path);
        path.reset();
        path.addCircle(mWidth/2,mWidth/2,mWidth/2,Path.Direction.CW);
        canvas.clipPath(path, Region.Op.XOR);

        super.onDraw(canvas);

        // 画内圆
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth/2,mWidth/2,innerR+innerL,paint);

        // 画外圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(outL);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2-outL,paint);
    }
}
