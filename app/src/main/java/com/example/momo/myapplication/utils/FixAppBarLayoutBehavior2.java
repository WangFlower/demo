package com.example.momo.myapplication.utils;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.momo.myapplication.view.CoordinatorLayoutCompat;

/**
 * Created by wang.renguang on 2018/7/10.
 */
public class FixAppBarLayoutBehavior2 extends CoordinatorLayout.Behavior {

    private boolean isDownAction;
    private boolean isHandling;
    private CoordinatorLayoutCompat coordinatorLayoutCompat;


    private int mParentHeight;      // AppBarLayout的初始高度
    private boolean isScrolling = false;
    private boolean isExpand = false;

    private boolean needHanding = false;

    private int height = UIUtils.getPixels(300);
    private int offset = 0;

    public FixAppBarLayoutBehavior2() {
        super();
    }

    public FixAppBarLayoutBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTranslationY());
        child.setTranslationY(translationY);
        return true;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("wangrenguang","onNestedScroll ");
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        Log.i("wangrenguang","onNestedScroll ");
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }
}
