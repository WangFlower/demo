package com.example.momo.myapplication.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.momo.myapplication.R;

/**
 * Created by MOMO on 16/12/28.
 */

public class MomentShareButton extends View {

    private Point center = new Point();
    private Context context;
    // 背景图片
    private Drawable drawable;
    //文字
    private String text = "1";
    private TextPaint textPaint;
    //文字paddingleft 文字paddingright
    private float textPadding;
    private float textSize;
    //文字背景
    private Paint textBgPaint;
    private int textBg = Color.TRANSPARENT;
    private RectF textBackground = new RectF();
    //文字背景padding
    private float textBgPaddingTop;
    private float textBgPaddingRight;
    // 文字背景外围一层透明padding
    private float textBgPadding;


    public MomentShareButton(Context context) {
        super(context);
        init(context);
    }

    public MomentShareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MomentShareButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MomentShareButton(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        this.context = context;
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(getResources().getColor(R.color.text));
        textSize = getPixels(8);
        textPaint.setTextSize(textSize);


        textBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textBgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        textPadding = getPixels(2);
        textBgPaddingTop = getPixels(10);
        textBgPadding = getPixels(2);
        textBgPaddingRight = getPixels(5);
        drawable = this.context.getResources().getDrawable(R.drawable.ic_moment_transmit);

    }

    public int getPixels(float dip) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                getResources().getDisplayMetrics()));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        final float tw = width * 0.33f;
        textBackground.set(width - tw - textBgPaddingRight, textBgPaddingTop, width -
                textBgPaddingRight, textBgPaddingTop + tw * 0.5f);

    }

    public void setText(String content) {
        text = content;
        invalidate();
    }

    private void setDrawable(Drawable drawable) {
        center.set(getMeasuredHeight() / 2, getMeasuredHeight() / 2);
        final int iw = drawable.getIntrinsicWidth();
        final int ih = drawable.getIntrinsicHeight();
        Rect rect = new Rect();
        rect.left = center.x - iw / 2;
        rect.top = center.y - ih / 2;
        rect.right = center.x + iw / 2;
        rect.bottom = center.y + ih / 2;
        drawable.setBounds(rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setDrawable(drawable);
        drawable.draw(canvas);
        drawText(canvas);

    }

    private void drawText(Canvas canvas) {
        if (text != null) {
            float textLenght = textPaint.measureText(text);
            measureTextRect(textLenght);

            textBackground.inset(-textBgPadding, -textBgPadding);
            textBgPaint.setColor(getResources().getColor(R.color.bg));
            canvas.drawRoundRect(textBackground, textBackground.width(), textBackground.width(),
                    textBgPaint);

            textBgPaint.setColor(getResources().getColor(R.color.textbg));
            textBackground.inset(textBgPadding, textBgPadding);
            canvas.drawRoundRect(textBackground, textBackground.width(), textBackground.width(),
                    textBgPaint);

            float x = textBackground.left + (textBackground.width() - textPaint.measureText(text)
            ) / 2;
            float y = textBackground.top + (textBackground.height() - (textPaint.descent() +
                    textPaint
                    .ascent())) / 2;

            canvas.drawText(text, x, y, textPaint);
        }
    }

    private void measureTextRect(float textLength) {
        final float mw = textLength + textPadding * 2;
        final float mh = textSize + textPadding * 2;
        final int width = getMeasuredWidth();
        if (mw > textBackground.width()) {
            textBackground.set(width - mw - textBgPaddingRight, textBackground.top, width -
                    textBgPaddingRight, textBackground.bottom);
        }
        if (mh > textBackground.height()) {
            textBackground.set(textBackground.left, textBgPaddingTop, textBackground.right, mh +
                    textBgPaddingTop);
        }
    }
}
