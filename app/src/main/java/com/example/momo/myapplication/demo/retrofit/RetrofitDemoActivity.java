package com.example.momo.myapplication.demo.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.view.AutoScrollTextView;
import com.example.momo.myapplication.view.MarqueeTextView;
import com.example.momo.myapplication.view.MarqueeTextView2;

import io.reactivex.internal.schedulers.RxThreadFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_demo);
//        request();
//        request1();
        TextView t = findViewById(R.id.text);
        t.setMovementMethod(new ScrollingMovementMethod());
        MarqueeTextView2 marqueeTextView = findViewById(R.id.autotxt);
        marqueeTextView.start();
//        marqueeTextView.setScrollWidth(800);
//        marqueeTextView.setCoordinateY(150);
//        marqueeTextView.setCurrentPosition(800);//设置滚动信息从滚动区域的右边出来
//        marqueeTextView.setSpeed(1);
//        marqueeTextView.setText("我是滚动字幕啊12345");
    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

    private void request1(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<Translation1> call = request.getCall1("I LOVE YOU");
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }
}
