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
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.momo.myapplication.R;

import static android.R.attr.width;

/**
 * Created by MOMO on 16/12/28.
 */

public class MomentLikeButton extends View {

    private boolean isMeasure = false;
    private Point center = new Point();

    private int with;
    private int height;



    private Context context;

    // 背景图片
    private Drawable drawable ;

    //文字
    private String text = "18";
    private TextPaint textPaint;
    //文字paddingleft 文字paddingright
    private float textPadding ;

    private float textMarginTop;
    private float textMarginRight;

    //文字背景
    private Paint textBgPaint ;
    private int textBg  = Color.WHITE;
    private RectF textBackground = new RectF();
    //文字颜色
    private int textColor = Color.TRANSPARENT;





    public MomentLikeButton(Context context) {
        super(context);
        init(context);
    }

    public MomentLikeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MomentLikeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MomentLikeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        textPaint.setTextSize(getPixels(19));
        textBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textBgPaint.setColor(textBg);
        textPaint.setColor(textColor);
        textPadding = getPixels(2);
        textMarginTop = getPixels(10);
        textMarginRight = getPixels(2);
        drawable = this.context.getResources().getDrawable(R.drawable.ic_moment_transmit);

    }

    public int getPixels(float dip) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics()));
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(isMeasure){
            return;
        }
        isMeasure = true;
        with = this.getMeasuredWidth();
        height = this.getMeasuredHeight();

        textBackground.set(0,0,with,height/2);

        center.set(with/2,height/2);
        setDrawable(drawable);

    }

    private void setDrawable(Drawable drawable){
        final int iw = drawable.getIntrinsicWidth();
        final int ih = drawable.getIntrinsicHeight();
        log("setDrawable",""+iw);
        log("setDrawable",""+ih);
        Rect rect = new Rect();
        rect.left = center.x - iw/2;
        rect.top = center.y - ih/2;
        rect.right = center.x + iw/2;
        rect.bottom = center.y + ih/2;
        drawable.setBounds(rect);
    }

    private void log(String tag,String s){
        Log.i("wangrenguang",tag+s);
    }

    public void setText(String s){

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.draw(canvas);
        drawText(canvas);

    }

    private void drawText(Canvas canvas){
        if(text!=null){
            canvas.save();
            float textLenght = textPaint.measureText(text);

            textBackground.set(with-textLenght-textPadding*2,textMarginTop,width-textMarginRight,width * 0.33f*0.5f);
            textBackground.inset(-textPadding, -textPadding);
            canvas.drawRoundRect(textBackground,textBackground.width(),textBackground.width(),textBgPaint);

            textBackground.inset(textPadding,textPadding);
            canvas.drawRoundRect(textBackground,textBackground.width(),textBackground.width(),textPaint);


            float x = (textBackground.width()-textPaint.measureText(text))/2;
            float y = (textBackground.height()-(textPaint.descent()+textPaint.ascent()))/2;
            canvas.drawText(text,x,y,textPaint);
            canvas.restore();
        }
    }
}
