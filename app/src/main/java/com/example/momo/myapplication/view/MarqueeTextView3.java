package com.example.momo.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeTextView3 extends TextView {

    private boolean mStopMarquee;
    private String mText = "";
    private float mCoordinateX = 800;
    private float mCoordinateY = 150;
    private float mTextWidth;
    private int mScrollWidth = 800;

    private int fps = 1000 / 30;
    private int speed = 4;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (mCoordinateX < (-mTextWidth)) {
                        mCoordinateX = mScrollWidth;
                        invalidate();
                        if (!mStopMarquee) {
                            sendEmptyMessageDelayed(0, 500);
                        }
                    } else {
                        mCoordinateX -= speed;
                        invalidate();
                        if (!mStopMarquee) {
                            sendEmptyMessageDelayed(0, fps);
                        }
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

    public MarqueeTextView3(Context context) {
        super(context);
    }

    public MarqueeTextView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MarqueeTextView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init() {
        if (getText() != null) {
            this.mText = getText().toString();
        }
        mTextWidth = getPaint().measureText(mText);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    public void start() {
        if (mHandler.hasMessages(0)) {
            mHandler.removeMessages(0);
        }
        mHandler.sendEmptyMessageDelayed(0, 10);
    }

    @Override
    protected void onDetachedFromWindow() {
        mStopMarquee = true;
        mHandler.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mCoordinateX, getTranslationY());
        super.onDraw(canvas);
    }


}