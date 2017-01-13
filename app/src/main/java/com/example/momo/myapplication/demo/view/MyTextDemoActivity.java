package com.example.momo.myapplication.demo.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.momo.myapplication.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MOMO on 16/12/30.
 */

public class MyTextDemoActivity extends AppCompatActivity {
    MomentShareButton momentShareButton;
    int a = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_text_demo);

        momentShareButton = (MomentShareButton) findViewById(R.id.sam);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("xxx/dd/djpg");
        arrayList.add("xxx/dd/djpg.gif");
        arrayList.add("xxx.gif");
        arrayList.add("x/.dasdsa/0/xx.gif");
        for(String text:arrayList){
            Pattern mPattern = Pattern.compile("([^\\.]*)\\.([^\\.]*)");
            Log.i("wang",""+text);
            Matcher matcher = mPattern.matcher(text);
            if(matcher.matches()){
                Log.i("wang","---"+text);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        handler.sendEmptyMessageDelayed(1,1000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s ;
            a = a * 5;
            int w = a/10000;
            if(w>0){
                s = w+"万";
            } else {
                s = ""+a;
            }
            momentShareButton.setText(s);
            handler.sendEmptyMessageDelayed(1,1000);

//            TextView textView = (TextView) findViewById(R.id.ddd);
//            GradientDrawable gradientDrawable = (GradientDrawable) textView.getBackground();
//            gradientDrawable.setColor(Color.RED);
        }
    };
}
