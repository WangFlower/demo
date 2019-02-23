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


        lottieAnimationView = (LottieAnimationView) findViewById(R.id.loadingview);

//        lottieAnimationView.setImageAssetsFolder("lottie/images");
//        lottieAnimationView.setAnimation("lottie/s.json");
//        lottieAnimationView.loop(true);
//        lottieAnimationView.playAnimation();

//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lottieAnimationView.setAnimation("lottie/animation-w286-h430.json");
//                lottieAnimationView.setVisibility(View.VISIBLE);
//                lottieAnimationView.loop(true);
//                lottieAnimationView.star();
//            }
//        });

    }
}
