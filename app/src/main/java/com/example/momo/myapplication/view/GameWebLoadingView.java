package com.example.momo.myapplication.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.example.momo.myapplication.R;

/**
 * Created by wangrenguang on 2017/8/5.
 */

public class GameWebLoadingView extends AppCompatImageView {

    private final int[] mBgRes = {R.drawable.game_web_loading_01, R.drawable.game_web_loading_02, R.drawable
            .game_web_loading_03};
    private ObjectAnimator objectAnimator;
    private int mCurrentIndex = 0;

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
        objectAnimator = ObjectAnimator.ofInt(this, "rotationY", 0, 360);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (mCurrentIndex >= mBgRes.length) {
                    mCurrentIndex = 0;
                }
                if (value == 180) {
                    GameWebLoadingView.this.setImageResource(mBgRes[mCurrentIndex]);
                }
            }
        });

    }

    public void rotationY(int rotationX) {
        super.setRotationX(rotationX);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            objectAnimator.start();
        } else {
            objectAnimator.end();
        }
    }

}
