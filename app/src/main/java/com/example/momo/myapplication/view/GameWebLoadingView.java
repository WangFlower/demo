package com.example.momo.myapplication.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.momo.myapplication.R;

/**
 * Created by wangrenguang on 2017/8/5.
 */

public class GameWebLoadingView extends ImageView implements ValueAnimator.AnimatorUpdateListener {

    private static final float CHANGE_OFFSET = 0.05f;

    private final int[] mBgRes = {R.drawable
            .game_web_loading_03, R.drawable.game_web_loading_01, R.drawable.game_web_loading_02};
    private ValueAnimator valueAnimator;
    private int mCurrentIndex = 0;

    private Camera mCamera;
    private Matrix cameraMatrix;

    private float changeDegree = 90;
    private float rotateDegree;

    public GameWebLoadingView(Context context) {
        super(context);
        init();
    }

    public GameWebLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameWebLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mCamera = new Camera();
        cameraMatrix = new Matrix();

        valueAnimator = ObjectAnimator.ofFloat(0, 180);
        valueAnimator.setDuration(1500);
        valueAnimator.start();
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCamera.save();
        mCamera.rotateY(rotateDegree);
        mCamera.getMatrix(cameraMatrix);
        mCamera.restore();
        final int cx = getWidth() >> 1;
        final int cy = getHeight() >> 1;
        cameraMatrix.preTranslate(-cx, -cy);
        cameraMatrix.postTranslate(cx, cy);

        canvas.concat(cameraMatrix);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            valueAnimator.start();
        } else {
            valueAnimator.end();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        final float value = (float) animation.getAnimatedValue();
        final float oldDegree = rotateDegree;
        rotateDegree = value;
        if (rotateDegree >= 90 && rotateDegree <= 180) {
            rotateDegree += 180;
        }
        final float changeDegree = this.changeDegree * (1 - CHANGE_OFFSET);
        if (oldDegree < changeDegree && value >= changeDegree) {
            if (mCurrentIndex >= mBgRes.length) {
                mCurrentIndex = 0;
            }
            GameWebLoadingView.this.setImageResource(mBgRes[mCurrentIndex]);
            mCurrentIndex++;
        }
        invalidate();
    }
}
