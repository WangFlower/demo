package com.example.momo.myapplication.monitor;

import android.os.Build;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Choreographer;

import java.util.concurrent.TimeUnit;

/**
 * @author wang.renguang
 * @time 2019/2/20
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class BlockMonitor implements Choreographer.FrameCallback {

    private static BlockMonitor instance = new BlockMonitor();


    private long lastFrameTimeNanos = 0L;

    private BlockMonitor() {

    }

    public static BlockMonitor getInstance() {
        return instance;
    }

    public void start() {
        Choreographer.getInstance().postFrameCallback(this);
    }


    @Override
    public void doFrame(long frameTimeNanos) {
        Log.i("wangrenguang", "doFrame:" + frameTimeNanos);
        if (lastFrameTimeNanos == 0L) {
            lastFrameTimeNanos = frameTimeNanos;
        } else {
            long diffms = TimeUnit.MILLISECONDS.convert(frameTimeNanos - lastFrameTimeNanos, TimeUnit.NANOSECONDS);
            long froppedCount = 0L;
            Log.i("wangrenguang", "diffms:" + diffms);
            if (diffms > 16.6F) {
                froppedCount = (int) (diffms / 16.6);
            }
            Log.i("wangrenguang", "froppedCount:" + froppedCount);
            if (froppedCount >= 5) {
                dump();
            }
            lastFrameTimeNanos = 0L;
        }

        Choreographer.getInstance().postFrameCallback(this);
    }

    private void dump() {
        StackTraceElement[] stackTraceElements = Looper.getMainLooper().getThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTraceElements) {
            Log.i("wangrenguang", stackTraceElement.toString());
        }
    }


}
