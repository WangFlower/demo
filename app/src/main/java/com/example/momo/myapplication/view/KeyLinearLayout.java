package com.example.momo.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class KeyLinearLayout extends LinearLayout{
    public KeyLinearLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public KeyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public KeyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    private void init(){
//        this.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("wangrenguang","KeyLinearLayout onTouch ");
//                return false;
//            }
//        });

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wangrenguang","KeyLinearLayout onClick ");
            }
        });
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
        Log.i("wangrenguang","KeyLinearLayout dispatchKeyEvent "+event.getAction());
        return super.dispatchKeyEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("wangrenguang","KeyLinearLayout onTouchEvent "+event.getAction());
        return super.onTouchEvent(event);
    }

}
