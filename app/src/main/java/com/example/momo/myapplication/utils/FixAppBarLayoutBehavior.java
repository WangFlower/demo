package com.example.momo.myapplication.utils;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.momo.myapplication.view.CoordinatorLayoutCompat;

/**
 * Created by wang.renguang on 2018/7/10.
 */
public class FixAppBarLayoutBehavior extends AppBarLayout.Behavior {

    private boolean isDownAction;
    private boolean isHandling;
    private CoordinatorLayoutCompat coordinatorLayoutCompat;


    private int mParentHeight;      // AppBarLayout的初始高度
    private boolean isScrolling = false;
    private boolean isExpand = false;

    private boolean needHanding = false;

    private int height = UIUtils.getPixels(300);
    private int offset = 0;

    public FixAppBarLayoutBehavior() {
        super();
    }

    public FixAppBarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        Log.i("wangrenguang","layoutDependsOn dependency "+dependency);
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        Log.i("wangrenguang","onDependentViewChanged dependency "+dependency);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i("wangrenguang","onOffsetChanged offset = "+offset);
                offset = verticalOffset;
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    // 收缩状态
                    isExpand = true;
                    needHanding = true;
                } else if (verticalOffset == 0) {
                    // 展开状态
                    isExpand = false;
                    needHanding = false;
                }

            }
        });

        return super.onLayoutChild(parent, abl, layoutDirection);
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
//        Log.i("wangrenguang","onNestedPreScroll dy = "+dy);
//        Log.i("wangrenguang","onNestedPreScroll ---- = "+(child.getTotalScrollRange()- Math.abs(offset)));
//        Log.i("wangrenguang","onNestedPreScroll 20 = "+UIUtils.getPixels(20));
//        Log.i("wangrenguang","onNestedPreScroll getHeight = "+child.getHeight());
//        Log.i("wangrenguang","onNestedPreScroll getTotalScrollRange = "+child.getTotalScrollRange());
//        Log.i("wangrenguang","onNestedPreScroll coordinatorLayout = "+coordinatorLayout.getHeight());
        // 1.mTargetView不为null
        // 2.是向下滑动，dy<0表示向下滑动
        // 3.AppBarLayout已经完全展开，child.getBottom() >= mParentHeight
        if (dy < 0 && needHanding &&  child.getTotalScrollRange()- Math.abs(offset) > UIUtils.getPixels(20)) {
            Log.i("wangrenguang","setExpanded ");
            needHanding = false;
            child.setExpanded(true);
        } else {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        }
    }
}
