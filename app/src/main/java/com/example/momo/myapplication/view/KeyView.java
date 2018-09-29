package com.example.momo.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

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


    private FilterEvent filterEvent;
    private CheckForLongPress checkForLongPress = new CheckForLongPress();
    private boolean mHasPerformedLongPress = false;


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("wangrenguang","KeyView dispatchTouchEvent "+event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                postDelayed(checkForLongPress, ViewConfiguration.getLongPressTimeout());
                break;
            case MotionEvent.ACTION_UP:
                if(!mHasPerformedLongPress){
                    removeCallbacks(checkForLongPress);
                    onClickEvent();
                } else {
                    onLongPressEndEvent();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                removeCallbacks(checkForLongPress);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private void onLongPressStartEvent(){
        mHasPerformedLongPress = true;
        if(filterEvent!=null){
            filterEvent.onLongPressStartEvent();
        }
    }


    private void onLongPressEndEvent(){
        mHasPerformedLongPress = false;
        if(filterEvent!=null){
            filterEvent.onLongPressEndEvent();
        }
    }

    private void onClickEvent(){
        if(filterEvent!=null){
            filterEvent.onClickEvent();
        }
    }

    public void setFilterEvent(FilterEvent filterEvent){
        this.filterEvent = filterEvent;
    }


    public interface FilterEvent{
        void onLongPressStartEvent();
        void onLongPressEndEvent();
        void onClickEvent();
    }


    private class CheckForLongPress implements Runnable{

        @Override
        public void run() {
            onLongPressStartEvent();
        }
    }
}
