package com.example.momo.myapplication.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wang.renguang on 2018/7/10.
 */
public class MyAppBarLayout extends AppBarLayout{
    public MyAppBarLayout(Context context) {
        super(context);
    }

    public MyAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return super.startNestedScroll(axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }
}
