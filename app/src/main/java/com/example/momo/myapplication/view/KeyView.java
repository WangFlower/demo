package com.example.momo.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class KeyView extends View{
    public KeyView(Context context) {
        super(context);
    }

    public KeyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KeyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("wangrenguang","KeyView onTouchEvent "+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("wangrenguang","KeyView dispatchKeyEvent "+event.getAction());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("wangrenguang","KeyView dispatchTouchEvent "+event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
