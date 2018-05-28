package com.example.momo.myapplication.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.momo.myapplication.utils.UIUtils;

/**
 * Created by wang.renguang on 2018/5/7.
 */

public class CropByPathImageView extends ImageView {

    private static final int DEFAULT_VIEW_WIDTH = UIUtils.getPixels(57);
    private static final int DEFAULT_VIEW_HEIGHT = UIUtils.getPixels(57);

    private int innerR = UIUtils.getPixels(4.5f);

    private int innerL = UIUtils.getPixels(1.5f);
    private int outL = UIUtils.getPixels(2f);
    private Path path = new Path();
    private Paint paint;

    private int mWidth;

    private ValueAnimator animator;
    private float rotate;

    public CropByPathImageView(Context context) {
        super(context);
        init();
    }

    public CropByPathImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CropByPathImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CropByPathImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        animator = ObjectAnimator.ofFloat(0f, 360f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rotate = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(100);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(DEFAULT_VIEW_WIDTH, widthMeasureSpec);
        int height = measureDimension(DEFAULT_VIEW_HEIGHT, heightMeasureSpec);
        mWidth = Math.min(width, height);
        setMeasuredDimension(mWidth, mWidth);
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
        path.reset();
        // 裁切画布
        path.moveTo(mWidth / 2, mWidth / 2);
        path.addCircle(mWidth / 2, mWidth / 2, innerR, Path.Direction.CW);
        canvas.clipPath(path);
        path.reset();
        path.addCircle(mWidth / 2, mWidth / 2, mWidth / 2, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.XOR);

        canvas.rotate(rotate, mWidth/2,mWidth/2);
        super.onDraw(canvas);

        // 画内圆
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth / 2, mWidth / 2, innerR + innerL, paint);

        // 画外圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(outL);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - outL, paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    public void start() {
        animator.start();
    }

    public void stop() {
        animator.cancel();
    }

}
