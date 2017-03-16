package com.example.momo.myapplication.demo.edittext;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.example.momo.myapplication.R;
import com.jakewharton.scalpel.ScalpelFrameLayout;

import java.util.Date;

import hugo.weaving.DebugLog;

/**
 * Created by MOMO on 16/10/31.
 */

public class SamActivity extends Activity {

    ScalpelFrameLayout scalpelFrameLayout;
    private WindowManager.LayoutParams windowParams;
    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.sam, null);
        View view1 = getLayoutInflater().inflate(R.layout.path, null);
        setContentView(view);
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowParams = new WindowManager.LayoutParams();
        windowParams.dimAmount = 0.8f;
        //避免权限问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(Build.VERSION.SDK_INT > 24){
                windowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                windowParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }

        } else {
            windowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        windowParams.format = PixelFormat.TRANSLUCENT;
        windowParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = 100;
        windowParams.height = 100;
        windowParams.x = 0;
        windowParams.y = 0;
        mWindowManager.addView(view1,windowParams);
        view1.setVisibility(View.VISIBLE);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i("wwwwwww2", "----" + isNeedShowPayConfirm());
        }
    };

    private static final long OVER_DATE = 5 * 1000;

    /**
     * @return
     */
    public boolean isNeedShowPayConfirm() {
        boolean result = true;

        long timeStr = getSharedPreferences("test", MODE_PRIVATE).getLong("sam", 0l);

        if (timeStr == 0l) {
            return true;
        }
        Date nowDate = new Date();
        long diff = nowDate.getDay()- timeStr;
        Log.i("wwwwwww3", "-----" + diff);
        // 是否过期
        if (diff < OVER_DATE) {
            result = false;
        }
        return result;
    }

    /**
     * @param payConfirm
     */
    public void setPayConfirm(boolean payConfirm) {
        long timeStr = 0l;
        if (!payConfirm) {
            timeStr = new Date().getDay();
            Log.i("wwwwwww4", "----" + new Date().getTime());
            Log.i("wwwwwww4", "----" + new Date().getDay());
        }
        getSharedPreferences("test", MODE_PRIVATE).edit().putLong("sam", timeStr).apply();

    }


}
