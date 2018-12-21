package com.example.momo.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MyTextView extends TextView implements View.OnClickListener {

    private int mWidth = 0;

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getText());
        int with = (int) getPaint().measureText(stringBuilder.toString());
        while (with < getWidth()) {
            stringBuilder.append("-");
        }
        stringBuilder.append("-");
        setText(stringBuilder.toString());
        setKeyListener(null);
    }

    public void onClick(View paramView) {
        TextUtils.TruncateAt localTruncateAt = TextUtils.TruncateAt.MARQUEE;
        setEllipsize(localTruncateAt);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }


    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public boolean isSelected() {
        return true;
    }


}
