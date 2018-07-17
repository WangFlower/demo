package com.example.momo.myapplication.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wang.renguang on 2018/7/6.
 */
public class AppBarLayoutOverScrollViewBehavior extends AppBarLayout.Behavior {

    private int mParentHeight;      // AppBarLayout的初始高度
    private boolean isScrolling = false;
    private boolean isExpand = false;

    private boolean needHanding = false;

    private int height = UIUtils.getPixels(300);
    private int offset = 0;


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
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

    public AppBarLayoutOverScrollViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

}
