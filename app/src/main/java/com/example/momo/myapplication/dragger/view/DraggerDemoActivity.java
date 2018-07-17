package com.example.momo.myapplication.dragger.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.momo.myapplication.dragger.presenter.IDraggerDemoPresenter;

import javax.inject.Inject;

/**
 * Created by wang.renguang on 2018/5/28.
 */
public class DraggerDemoActivity extends Activity {

    @Inject
    IDraggerDemoPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Glide.with(this).load("").into(new ImageView(this));
    }
}
