package com.example.momo.myapplication.demo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang.renguang on 2018/4/27.
 */

public class RecyclerDemo extends Activity {

    RecyclerView recyclerView;

    List<String> list = new ArrayList<>();

    Handler handler = new Handler();

    private AnimatorSet hideAnimator;
    private AnimatorSet showAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycle_demo);
        recyclerView = (RecyclerView) this.findViewById(R.id.cusom_swipe_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i=0;i<50;i++){
            list.add(""+i);
        }
        recyclerView.setAdapter(new MyAdapter(this,list));

        final View imageView = findViewById(R.id.goto_record_layout);



        showAnimator = new AnimatorSet() ;

        final ObjectAnimator sty = ObjectAnimator.ofFloat(imageView, "translationY", imageView.getTranslationY());
        final ObjectAnimator sah = ObjectAnimator.ofFloat(imageView, "alpha", 1f);
        showAnimator.setInterpolator(new LinearInterpolator());
        showAnimator.setDuration(300);
        showAnimator.play(sty).with(sah);

        hideAnimator = new AnimatorSet();
        final ObjectAnimator hsy = ObjectAnimator.ofFloat(imageView, "translationY", UIUtils.getPixels(176));
        final ObjectAnimator hah = ObjectAnimator.ofFloat(imageView, "alpha", 0f);
        hideAnimator.setInterpolator(new LinearInterpolator());
        hideAnimator.setDuration(300);
        hideAnimator.play(hsy).with(hah);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isVisible = true;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(Math.abs(dy)<UIUtils.getPixels(3)){
                    return;
                }
                if (dy > 0) {
                    if(!isVisible){
                        return;
                    }
                    isVisible = false;
                    showAnimator.cancel();
                    hideAnimator.start();
                } else if(dy<0){
                    if(isVisible){
                        return;
                    }
                    isVisible = true;
                    hideAnimator.cancel();
                    showAnimator.start();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.remove(3);
            }
        },3000);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


        private List<String> data;

        private Context context;

        public MyAdapter(Context context,List data){
            this.data = data;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.i("wangrenguang","onBindViewHolder "+data.size());
            holder.textView.setText(""+position);
        }

        @Override
        public int getItemCount() {
            Log.i("wangrenguang","getItemCount "+data.size());
            return data == null?0:data.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }



}
