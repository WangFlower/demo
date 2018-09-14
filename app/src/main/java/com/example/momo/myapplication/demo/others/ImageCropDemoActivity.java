package com.example.momo.myapplication.demo.others;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.momo.myapplication.BaseActivity;
import com.example.momo.myapplication.R;
import com.example.momo.myapplication.view.MusicImageView;

/**
 * Created by wang.renguang on 2018/5/7.
 */

public class ImageCropDemoActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_crop_demo);

        final MusicImageView imageView = (MusicImageView) findViewById(R.id.ddd);

        final MusicImageView imageView2 = (MusicImageView) findViewById(R.id.ccc);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.ball).copy(Bitmap.Config.ARGB_8888,true);

        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.eeee).copy(Bitmap.Config.ARGB_8888,true);


        imageView.setBimap(bitmap);
        imageView2.setBimap(bitmap2);


    }





}
