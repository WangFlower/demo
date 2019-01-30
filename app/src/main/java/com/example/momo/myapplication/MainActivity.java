package com.example.momo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.datastate_processor.State;
import com.example.momo.myapplication.demo.eventbus.EventBusDemoActivity;
import com.example.momo.myapplication.demo.others.DemoAvtivity;
import com.example.momo.myapplication.demo.others.ImageCropDemoActivity;
import com.example.momo.myapplication.demo.others.KeyEventDemoActivity;
import com.example.momo.myapplication.demo.others.LottieDemoActivity;
import com.example.momo.myapplication.demo.others.PickViewActivity;
import com.example.momo.myapplication.demo.others.RecyclerDemo;
import com.example.momo.myapplication.demo.others.RxDemoActivity;
import com.example.momo.myapplication.demo.others.TouchDemoActivity;
import com.example.momo.myapplication.demo.others.ViewDemoActivity;
import com.example.momo.myapplication.demo.others.ViewPageDemoActivity;
import com.example.momo.myapplication.demo.retrofit.RetrofitDemoActivity;
import com.example.momo.myapplication.dragger.view.DraggerDemoActivity;
import com.example.momo.myapplication.kot.demo.TaskActivity;
import com.example.mrouter.compiler.MRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2017/8/9.
 */
@MRouter("[main]")
public class MainActivity extends Activity {


    @State
    public int a = 0;
    @State
    public String name;
    @State
    public String[] names;
    @State
    private String priName;

    @State
    String defName;

    @State
    protected String proName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ItemModel> data = new ArrayList<>();

        data.add(new ItemModel("RetrofitDemo", RetrofitDemoActivity.class));
        data.add(new ItemModel("图片颜色处理", DemoAvtivity.class));
        data.add(new ItemModel("Rx demo", RxDemoActivity.class));
        data.add(new ItemModel("lottie", LottieDemoActivity.class));
        data.add(new ItemModel("横向分页的RecyclerView", PickViewActivity.class));
        data.add(new ItemModel("recycle demo", RecyclerDemo.class));
        data.add(new ItemModel("ImageCropDemoActivity", ImageCropDemoActivity.class));
        data.add(new ItemModel("dragger", DraggerDemoActivity.class));
        data.add(new ItemModel("ViewPage", ViewPageDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("EventBusDemoActivity",EventBusDemoActivity.class));
        data.add(new ItemModel("KeyEventDemoActivity",KeyEventDemoActivity.class));
        data.add(new ItemModel("KT",TaskActivity.class));
        data.add(new ItemModel("TouchDemoActivity", TouchDemoActivity.class));

        MyAdapter adapter = new MyAdapter(data, this);
        recyclerView.setAdapter(adapter);



    }

    static class MyAdapter extends RecyclerView.Adapter {

        private List<ItemModel> data;
        private Activity activity;

        public MyAdapter(List<ItemModel> data, Activity activity) {
            this.data = data;
            this.activity = activity;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.itemModel = data.get(position);
            myViewHolder.textView.setText(data.get(position).text);

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textView;
            ItemModel itemModel;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.startActivity(new Intent(activity, itemModel.alass));
                    }
                });
            }
        }
    }

    private static class ItemModel {
        String text;
        Class alass;

        public ItemModel(String text, Class c) {
            this.text = text;
            this.alass = c;
        }


    }
}
