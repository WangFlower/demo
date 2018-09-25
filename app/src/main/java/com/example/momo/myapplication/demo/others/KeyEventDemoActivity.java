package com.example.momo.myapplication.demo.others;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.momo.myapplication.R;

public class KeyEventDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.key_event_demop);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wangrenguang","onClick");
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("wangrenguang","KeyEventDemoActivity dispatchKeyEvent "+event.getAction());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("wangrenguang","KeyEventDemoActivity dispatchTouchEvent "+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onUserLeaveHint() {
        Log.i("wangrenguang","KeyEventDemoActivity onUserLeaveHint ");
        super.onUserLeaveHint();
    }

    @Override
    public void onUserInteraction() {
        Log.i("wangrenguang","KeyEventDemoActivity onUserInteraction ");
        super.onUserInteraction();
    }
}
