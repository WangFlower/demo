package com.example.momo.myapplication.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
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

public class MusicImageView extends ImageView {

    public static final int DEFAULT_VIEW_WIDTH = UIUtils.getPixels(57);
    public static final int DEFAULT_VIEW_HEIGHT = UIUtils.getPixels(57);


    private float innerR = UIUtils.getPixels(4.5f);

    private float innerL = UIUtils.getPixels(2f);
    private float outL = UIUtils.getPixels(3f);
    private Path path = new Path();
    private Paint paint;

    private int mWidth;
    private int mHeight;

    private ValueAnimator animator;
    private float rotate;

    public MusicImageView(Context context) {
        super(context);
        init();
    }

    public MusicImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MusicImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MusicImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        animator = ObjectAnimator.ofFloat(this,"rotation",0f,360f);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(100);
        animator.start();

        paint = new Paint();
        paint.setAntiAlias(true);
//        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(DEFAULT_VIEW_WIDTH, widthMeasureSpec);
        int height = measureDimension(DEFAULT_VIEW_HEIGHT, heightMeasureSpec);
        mWidth = Math.min(width, height);
        mHeight = mWidth;
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

    Bitmap bitmap;

    public void setBimap(Bitmap bmp){
        this.bitmap =crop(bmp,Math.min(bmp.getHeight(),bmp.getWidth()),mWidth!=0?mWidth:DEFAULT_VIEW_WIDTH, innerR);
        setImageBitmap(bitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        // 画内圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(innerL);
        canvas.drawCircle(mWidth / 2, mWidth / 2, innerR, paint);

        // 画外圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(outL);
        canvas.drawCircle(mWidth / 2, mWidth / 2, (mWidth-outL) / 2, paint);
    }


    public static Bitmap crop(Bitmap source,float src,float dst,float inner){
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        int targetDst = (int) dst;
        Bitmap target = Bitmap.createBitmap(targetDst, targetDst, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.save();
        canvas.drawCircle(dst / 2, dst / 2, inner, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        canvas.drawCircle(dst / 2, dst / 2, dst / 2, paint);
        canvas.restore();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        float scale = dst/src;
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);

        canvas.drawBitmap(source, matrix, paint);
        return target;
    }



    private long lastPlayTime = 0l;

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.clearAnimation();
    }

    public void start() {
        animator.setCurrentPlayTime(lastPlayTime);
        animator.start();
    }

    public void stop() {
        lastPlayTime = animator.getCurrentPlayTime();
        animator.cancel();
    }

}
