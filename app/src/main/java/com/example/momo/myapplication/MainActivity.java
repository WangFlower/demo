package com.example.momo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.momo.myapplication.demo.DemoAvtivity;
import com.example.momo.myapplication.demo.ImageCropDemoActivity;
import com.example.momo.myapplication.demo.LottieDemoActivity;
import com.example.momo.myapplication.demo.PickViewActivity;
import com.example.momo.myapplication.demo.RecyclerDemo;
import com.example.momo.myapplication.demo.RxDemoActivity;
import com.example.momo.myapplication.demo.ViewDemoActivity;
import com.example.momo.myapplication.demo.ViewPageDemoActivity;
import com.example.momo.myapplication.dragger.view.DraggerDemoActivity;
import com.example.momo.myapplication.utils.UIUtils;
import com.example.mrouter.compiler.MRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2017/8/9.
 */
@MRouter("[main]")
public class MainActivity extends Activity {

    private boolean isExpand = true;
    private boolean isHandle = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppBarLayout appBarLayout = findViewById(R.id.appbar_layout);
//
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//
//            int last = 0;
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                Log.i("wangrenguang","onOffsetChanged isExpand:"+isExpand+";isHandle="+isHandle+";verticalOffset="+verticalOffset);
//                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                    // 收缩状态
//                    isExpand = true;
//                    isHandle = false;
//                } else if (verticalOffset == 0) {
//                    // 展开状态
//                    isExpand = false;
//                    isHandle = false;
//                }
//
//                if(Math.abs(verticalOffset)-Math.abs(last)>UIUtils.getPixels(20) && !isHandle && isExpand){
//                    Log.i("wangrenguang","up scoll");
//                    isHandle = true;
//                    appBarLayout.setExpanded(true,false);
//                }
//                last = verticalOffset;
//            }
//        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        List<ItemModel> data = new ArrayList<>();
        data.add(new ItemModel("图片颜色处理", DemoAvtivity.class));
        data.add(new ItemModel("Rx demo", RxDemoActivity.class));
        data.add(new ItemModel("lottie", LottieDemoActivity.class));
        data.add(new ItemModel("横向分页的RecyclerView", PickViewActivity.class));
        data.add(new ItemModel("recycle demo", RecyclerDemo.class));
        data.add(new ItemModel("ImageCropDemoActivity", ImageCropDemoActivity.class));
        data.add(new ItemModel("dragger", DraggerDemoActivity.class));
        data.add(new ItemModel("ViewPage", ViewPageDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));
        data.add(new ItemModel("view demo",ViewDemoActivity.class));


        MyAdapter adapter = new MyAdapter(data, this);
        recyclerView.setAdapter(adapter);



    }

    public void setupStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && window != null && window.getDecorView() != null) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
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
