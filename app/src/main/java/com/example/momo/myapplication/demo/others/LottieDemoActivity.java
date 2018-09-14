package com.example.momo.myapplication.demo.others;

import android.animation.ValueAnimator;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.momo.myapplication.BaseActivity;
import com.example.momo.myapplication.R;

/**
 * Created by wang.renguang on 2018/6/29.
 */
public class LottieDemoActivity extends BaseActivity {

    LottieAnimationView lottieAnimationView;
    View layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottie_demo);
        layout = findViewById(R.id.layout);

        final ImageView imageView = findViewById(R.id.imageview);

        lottieAnimationView = (LottieAnimationView) findViewById(R.id.loadingview);
        lottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("wangrenguang","onAnimationUpdate");
            }
        });

//        lottieAnimationView.setImageAssetsFolder("lottie/images");
//        lottieAnimationView.setAnimation("lottie/s.json");
//        lottieAnimationView.loop(true);
//        lottieAnimationView.playAnimation();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setBackgroundResource(R.drawable.oval_7);
                lottieAnimationView.setAnimation("lottie/animation-w286-h430.json");
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.loop(true);
                lottieAnimationView.playAnimation();
            }
        });

    }
}
