package com.example.momo.myapplication.demo.others;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.view.ColorMatrixImageView;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wang.renguang on 16/11/28.
 *
 * 图片颜色处理
 */

public class DemoAvtivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar r;
    SeekBar a;
    SeekBar g;
    SeekBar b;
    ColorMatrixImageView colorMatrixImageView;

    CountDownLatch countDownLatch = new CountDownLatch(1);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path);

        r = (SeekBar) findViewById(R.id.r);
        r.setOnSeekBarChangeListener(this);
        a = (SeekBar) findViewById(R.id.a);
        a.setOnSeekBarChangeListener(this);
        g = (SeekBar) findViewById(R.id.g);
        g.setOnSeekBarChangeListener(this);
        b = (SeekBar) findViewById(R.id.b);
        b.setOnSeekBarChangeListener(this);
        colorMatrixImageView = (ColorMatrixImageView) findViewById(R.id.ssss);
        r.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("wangrenguang","1000");
            }
        },1000);

        r.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("wangrenguang","5000");
            }
        },5000);

        r.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("wangrenguang","10000");
            }
        },10000);

        r.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("wangrenguang","15000");
            }
        },15000);

        countDownLatch.countDown();

        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.countDown();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("wangrenguang","onResume");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("wangrenguang","onDestroy");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float rf = r.getProgress()/128f;
        float af = a.getProgress()/128f;
        float gf = g.getProgress()/128f;
        float bf = b.getProgress()/128f;
        colorMatrixImageView.update(rf,af,gf,bf);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
