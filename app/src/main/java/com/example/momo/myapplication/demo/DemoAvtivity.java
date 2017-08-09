package com.example.momo.myapplication.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.view.ColorMatrixImageView;

/**
 * Created by wang.renguang on 16/11/28.
 */

public class DemoAvtivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar r;
    SeekBar a;
    SeekBar g;
    SeekBar b;
    ColorMatrixImageView colorMatrixImageView;

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
