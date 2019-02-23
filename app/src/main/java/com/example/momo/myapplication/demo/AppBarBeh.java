package com.example.momo.myapplication.demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.View;

import com.example.momo.myapplication.utils.UIUtils;

/**
 * Created by wang.renguang on 2018/7/14
 */
public class AppBarBeh extends android.support.design.widget.AppBarLayout.Behavior{
    private int mParentHeight;      // AppBarLayout的初始高度
    private boolean isScrolling = false;
    private boolean isExpand = false;

    private boolean needHanding = false;

    private int height = UIUtils.getPixels(300);
    private int offset = 0;

    public AppBarBeh() {
        super();
    }

    public AppBarBeh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i("wangrenguang","onOffsetChanged "+offset);
                Log.i("wangrenguang","onOffsetChanged "+offset);
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
        Log.i("wangrenguang","onOffsetChanged "+abl.getTotalScrollRange());
        return super.onLayoutChild(parent, abl, layoutDirection);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
        Log.i("wangrenguang","onStopNestedScroll "+abl.getBottom()+";isExpand="+isExpand);
        if(needHanding && isExpand && abl.getBottom()>abl.getTotalScrollRange()/4){
            Log.i("wangrenguang","onStopNestedScroll ------ ");
            abl.setExpanded(true,true);
            return;
        }
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }


}
