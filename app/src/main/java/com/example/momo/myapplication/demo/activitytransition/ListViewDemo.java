package com.example.momo.myapplication.demo.activitytransition;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.demo.broadcastreceiver.BroadcastreceiverDemo;

/**
 * Created by MOMO on 17/2/24.
 */

public class ListViewDemo extends Activity {
    private static final String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
    };

    ListView listView;
    LinearLayout header;
    LinearLayout header1;
    ArrayAdapter arrayAdapter;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_demo);
        imageView = (ImageView) findViewById(R.id.ssss);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.feed_blue_tips);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("wangrenguang","onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("wangrenguang","onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("wangrenguang","onAnimationRepeat");
            }
        });
        imageView.startAnimation(animation);
//        listView = (ListView) findViewById(R.id.listview);
//        header = new LinearLayout(this);
//        listView.addHeaderView(header);
//        header1 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.listview_header1,null);
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
//        listView.setAdapter(arrayAdapter);
//        header.addView(header1);
        handler.sendEmptyMessageDelayed(1,500);

        Log.i("wangrenguang",""+getFormatNumberStr(100));
        Log.i("wangrenguang",""+getFormatNumberStr(1000));
        Log.i("wangrenguang",""+getFormatNumberStr(1100));
        Log.i("wangrenguang",""+getFormatNumberStr(1110));
        Log.i("wangrenguang",""+getFormatNumberStr(1010));

        Log.i("wangrenguang","-------------");

        Log.i("wangrenguang",""+getFormatNumberStr(20000));
        Log.i("wangrenguang",""+getFormatNumberStr(11010));
        Log.i("wangrenguang",""+getFormatNumberStr(29800));

        Log.i("wangrenguang","-------------");


        Log.i("wangrenguang",""+getFormatNumberStr(229800));
        Log.i("wangrenguang",""+getFormatNumberStr(200800));
        this.registerReceiver(new BroadcastreceiverDemo(),new IntentFilter("123"));
        this.registerReceiver(new BroadcastreceiverDemo(),new IntentFilter("234"));
        sendBroadcast(new Intent("123"));
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            header1.setVisibility(View.GONE);
            Log.i("wangrenguang","handleMessage1");
            imageView.setBackgroundResource(R.drawable.ball);
            Log.i("wangrenguang","handleMessage2");

        }
    };

    public static String getFormatNumberStr(long number) {
        if (number < 1000l) {
            return "" + number;
        }
        if (number < 10000l) {
            long t = number / 1000l;
            long h = (number - t * 1000l) / 100l;
            if(h>0){
                return t + "." + h + "K";
            } else {
                return t +"K";
            }

        }
        long w = number / 10000l;
        long t = (number - w * 10000l) / 1000l;
        if(t>0){
            return w + "." + t + "W";
        } else {
            return w + "W";
        }

    }


}
