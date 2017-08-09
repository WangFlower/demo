package com.example.momo.myapplication.pageview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.example.momo.myapplication.utils.UIUtils;

/**
 * Created by sam on 2017/7/31.
 */

public class PageGridView extends AdapterView {

    private Adapter mAdapter;

    private int mHCount = 4;
    private int mVCount = 1;

    // item params
    private int mItemWith;
    private int mItemHeight;
    private int mItemPadding;

    public PageGridView(Context context) {
        super(context);
        init();
    }

    public PageGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PageGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
    public PageGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mItemWith = UIUtils.getScreenWidth() / mHCount;
        mItemHeight = mItemWith;
    }

    @Override
    public Adapter getAdapter() {
        return this.mAdapter;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Padding
        if (mAdapter == null || mAdapter.getCount() <= 0) {
            return;
        }
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // height
//        View childView = mAdapter.getView(0,null,this);
//        measurePageChild(childView,0,);
        if (heightMode != MeasureSpec.EXACTLY) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mItemHeight * 2, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void measurePageChild(View childView, int position, int parentWidthMeasureSpec, int
            parentHeightMeasureSpec) {
        measureChild(childView, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }

//    class RecycleBin{
//        View[] mActiveViews;
//        ArrayList<View> mRecycleViews;
//
//
//        public void getRecycleView(){
//
//        }
//    }

}
