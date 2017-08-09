package com.example.momo.myapplication.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MOMO on 17/2/13.
 */

public class PlayIconView extends View{
    public PlayIconView(Context context) {
        super(context);
    }

    public PlayIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayIconView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }








}
