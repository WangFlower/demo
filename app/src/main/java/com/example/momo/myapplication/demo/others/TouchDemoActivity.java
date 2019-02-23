package com.example.momo.myapplication.demo.others;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.monitor.BlockMonitor;

public class TouchDemoActivity extends Activity {


    private float oldDist;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.touch_demo);
        Log.i("wangrenguang", "TouchDemoActivity");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("wangrenguang", "TouchDemoActivity");
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    private float getFingerSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
}
