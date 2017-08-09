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
import android.util.Log;
import android.widget.ImageView;

import com.example.momo.myapplication.R;

/**
 * Created by wangrenguang on 16/12/14.
 * 图片颜色处理
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

        // decode出来的图片 是不可编辑的
        baseBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ball);
        Log.i("wangrenguang", "baseBitmap " + baseBitmap.isMutable());

        // 下面两种方式可以创建出可以编辑的bitmap
//        afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), Bitmap
//                .Config.ARGB_8888);
//        Log.i("wangrenguang","baseBitmap "+afterBitmap.isMutable());
        afterBitmap = baseBitmap.copy(baseBitmap.getConfig(), true);
        Log.i("wangrenguang", "baseBitmap " + afterBitmap.isMutable());


        paint = new Paint();
        colorMatrix = new ColorMatrix();
        //给canvas 的bitmap 需要是可以编辑的  不然会 throw new IllegalStateException("Immutable bitmap passed to Canvas constructor");
        canvas = new Canvas(afterBitmap);
        setBitMap(colorArray);
    }

    public void setBitMap(float[] colorArray) {
        colorMatrix.setSaturation(0);
        colorMatrix.set(colorArray);
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
}
