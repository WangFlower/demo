package com.example.momo.myapplication.demo.others;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.momo.myapplication.BaseActivity;
import com.example.momo.myapplication.R;
import com.example.momo.myapplication.utils.UIUtils;
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


        ImageView image = findViewById(R.id.image);


//
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.ball).copy(Bitmap.Config.ARGB_8888,true);
//
//        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.eeee).copy(Bitmap.Config.ARGB_8888,true);
//
//
//        imageView.setBimap(bitmap);
//        imageView2.setBimap(bitmap2);


        image.setImageBitmap(createBitmap());


    }

    private Bitmap createBitmap(){

        String content = "@用户的昵称";
        String live = "陌陌影集";

        TextPaint namePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        namePaint.setTextSize(UIUtils.sp2pix(20));
        Typeface font = Typeface.create(content, Typeface.BOLD);
        namePaint.setTypeface(font);
        namePaint.setColor(Color.WHITE);

        TextPaint livePint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        livePint.setTextSize(UIUtils.sp2pix(15));
        livePint.setColor(Color.WHITE);



        Rect nameRect = new Rect();
        namePaint.getTextBounds(content, 0, content.length(), nameRect);
        int withName = nameRect.width();
        int heightName = nameRect.height();

        Rect liveRect = new Rect();
        livePint.getTextBounds(content, 0, content.length(), liveRect);
        int withLive = liveRect.width();
        int heightLive = liveRect.height();

        int with = Math.max(withName,withLive);
        int mergin = UIUtils.getPixels(13);
        int height = heightName+mergin+heightLive;

        Bitmap bitmap = Bitmap.createBitmap(with,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(content,0,heightName,namePaint);
        canvas.drawText(live,0,height-UIUtils.getPixels(2),livePint);
        return bitmap;

    }





}
