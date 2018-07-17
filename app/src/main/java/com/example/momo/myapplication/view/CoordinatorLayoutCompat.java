package com.example.momo.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chenwangwang on 2017/9/20.
 * 修复再android 6.0及以下系统，试图不可见之后仍然不断绘制的bug
 */
public class CoordinatorLayoutCompat extends CoordinatorLayout {

    private boolean mAttachedToWindow;
    private boolean mNeedPreDrawListener;
    public boolean mIsShow;

    public boolean isUpAction;

    public CoordinatorLayoutCompat(Context context) {
        super(context);
    }

    public CoordinatorLayoutCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinatorLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float initX, initY;
    private OnMoveListener onMoveListener;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            initX = ev.getX();
            initY = ev.getY();
        }

        if(ev.getAction() == MotionEvent.ACTION_UP){
            isUpAction = true;
        } else {
            isUpAction = false;
        }
        float deltaX = ev.getX() - initX;
        float deltaY = ev.getY() - initY;
        if (onMoveListener != null) {
            onMoveListener.onMove(initX, initY, deltaX, deltaY);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE) {
            if (!mIsShow) {
                mIsShow = isShown();
                // 因为在onMeasure的时候回调用ensurePreDrawListener方法，所以这里可以不用处理
                /*if (!mNeedPreDrawListener) {
                    ensurePreDrawListener();
                }*/
            }
        } else {
            if (mIsShow) {
                mIsShow = false;

            }
        }
    }

    @Override
    public boolean startNestedScroll(int axes) {
        Log.i("wangrenguang","startNestedScroll axes="+axes);
        return super.startNestedScroll(axes*2);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        Log.i("wangrenguang","dispatchNestedPreScroll  dy=" +dy);
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean hasNestedScrollingParent() {
        Log.i("wangrenguang","hasNestedScrollingParent" );
        return super.hasNestedScrollingParent();
    }

    @Override
    public void stopNestedScroll() {
        Log.i("wangrenguang","stopNestedScroll" );
        super.stopNestedScroll();
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i("wangrenguang","onStartNestedScroll "+nestedScrollAxes );
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        Log.i("wangrenguang","dispatchNestedScroll" );
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }


    @Override
    public void computeScroll() {
        Log.i("wangrenguang","computeScroll" );
        super.computeScroll();
    }


    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("wangrenguang","onNestedScroll dyConsumed="+target );
        Log.i("wangrenguang","onNestedScroll dyUnconsumed="+dyUnconsumed );
        if(dyUnconsumed<0){
            super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed*3);
            return;
        }
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.i("wangrenguang","onNestedScrollAccepted child="+child );
        Log.i("wangrenguang","onNestedScrollAccepted nestedScrollAxes="+nestedScrollAxes );
        super.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    protected int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }



    @Override
    public void onDetachedFromWindow() {
        mAttachedToWindow = false;
        super.onDetachedFromWindow();
    }

    @Override
    public void onAttachedToWindow() {
        mAttachedToWindow = true;
        super.onAttachedToWindow();

    }


    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }

    public interface OnMoveListener {
        void onMove(float initX, float initY, float deltaX, float deltaY);
    }
}
