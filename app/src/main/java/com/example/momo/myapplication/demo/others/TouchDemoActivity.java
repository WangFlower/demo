package com.example.momo.myapplication.demo.others;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

import com.example.momo.myapplication.R;

public class TouchDemoActivity extends Activity {


    private float oldDist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.touch_demo);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getPointerCount() == 2) {
            Log.i("wangrenguang", "getAction" + event.getAction());
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = getFingerSpacing(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float newDist = getFingerSpacing(event);
                    Log.i("wangrenguang", "-------------");
                    Log.i("wangrenguang", "newDist" + newDist);
                    Log.i("wangrenguang", "oldDist" + oldDist);
                    Log.i("wangrenguang", "" + (newDist - oldDist));
                    Log.i("wangrenguang", "-------------");
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    private float getFingerSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
}
