package com.example.momo.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.utils.UIUtils;

/**
 * 带分割线的GridView
 */
public class LineGridView extends GridView {

    private int mSplineColor = R.color.grid_view_sp_line;
    private float mSplineWidth = UIUtils.getPixels(0.5f);

    public LineGridView(Context context) {
        super(context);
    }

    public LineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawSpLine(canvas);
    }

    /**
     * 画网格分割线
     *
     * @param canvas
     */
    private void drawSpLine(Canvas canvas) {
        int childCount = getChildCount();//子view的总数
        if (childCount <= 0) {
            return;
        }
        View localView1 = getChildAt(0);
        int column = getWidth() / localView1.getWidth();//计算出一共有多少列
        Paint localPaint;//画笔
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setStrokeWidth(mSplineWidth);
        localPaint.setColor(getContext().getResources().getColor(mSplineColor));//设置画笔的颜色
        for (int i = 0; i < childCount; i++) {//遍历子view
            View cellView = getChildAt(i);//获取子view
            if ((i + 1) % column == 0) {
                //画子view底部横线
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(),
                        localPaint);
            } else {//如果view不是最后一行
                //画子view的右边竖线
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(),
                        localPaint);
                //画子view的底部横线
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(),
                        localPaint);
            }
        }
    }

    /**
     * 设置网格分割线颜色和宽度
     *
     * @param color
     * @param lineWidth
     */
    public void setSpLineColor(int color, float lineWidth) {
        this.mSplineColor = color;
        this.mSplineWidth = lineWidth;
        postInvalidate();
    }

}
