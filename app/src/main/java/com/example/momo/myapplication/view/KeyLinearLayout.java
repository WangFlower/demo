package com.example.momo.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class KeyLinearLayout extends LinearLayout{
    public KeyLinearLayout(@NonNull Context context) {
        super(context);
    }

    public KeyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KeyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("wangrenguang","KeyLinearLayout onInterceptTouchEvent "+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("wangrenguang","KeyLinearLayout dispatchTouchEvent "+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("wangrenguang","KeyLinearLayout dispatchTouchEvent "+event.getAction());
        return super.dispatchKeyEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("wangrenguang","KeyLinearLayout dispatchTouchEvent "+event.getAction());
        return super.onTouchEvent(event);
    }

}
