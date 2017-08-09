package com.example.momo.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.momo.myapplication.R;

/**
 * Created by MOMO on 16/12/14.
 * http://blog.csdn.net/sjf0115/article/details/8698619
 * <p>
 * https://my.oschina.net/u/1377657/blog/400503
 * <p>
 *
 */

public class ColorMatrixImageView extends ImageView {

    private Paint paint;
    private Bitmap baseBitmap;
    private Bitmap afterBitmap;
    private Canvas canvas;
    private ColorMatrix colorMatrix;

    private float[] colorArray = {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0};

    public ColorMatrixImageView(Context context) {
        super(context, null);
    }

    public ColorMatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorMatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColorMatrixImageView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        baseBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ball);
        afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), Bitmap
                .Config.ARGB_8888);

        paint = new Paint();
        colorMatrix = new ColorMatrix();
        canvas = new Canvas(afterBitmap);


        setBitMap(colorArray);
    }

    public void setBitMap(float[] colorArray) {
        colorMatrix.set(colorArray);
        colorMatrix.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(baseBitmap, 0, 0, paint);
        setImageBitmap(afterBitmap);
    }

    /**
     * @param r 0< r/a/g/b <2
     * @param a
     * @param g
     * @param b
     */
    public void update(float r, float a, float g, float b) {
        float[] colorArray = {
                r, 0, 0, 0, 0,
                0, a, 0, 0, 0,
                0, 0, g, 0, 0,
                0, 0, 0, b, 0};
        setBitMap(colorArray);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
