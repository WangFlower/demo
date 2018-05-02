package com.example.momo.myapplication.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.view.PageIndicatorView;
import com.example.momo.myapplication.view.PageRecyclerView;
import com.example.mrouter.compiler.MRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangrenguang on 17/2/13.
 * 横向翻页RecyclerView
 *
 */
public class PickViewActivity extends Activity {
    private PageRecyclerView mRecyclerView = null;
    private List<String> dataList = null;
    private PageRecyclerView.PageAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cusom_swipe_view);
        initData();

        mRecyclerView = (PageRecyclerView) findViewById(R.id.cusom_swipe_view);
        // 设置指示器
        mRecyclerView.setIndicator((PageIndicatorView) findViewById(R.id.indicator));
        // 设置行数和列数
        mRecyclerView.setPageSize(3, 3);
        // 设置页间距
        mRecyclerView.setPageMargin(30);
        // 设置数据
        mRecyclerView.setAdapter(myAdapter = mRecyclerView.new PageAdapter(dataList, new
                PageRecyclerView.CallBack() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(PickViewActivity.this).inflate(R.layout.item, parent,
                        false);
                return new MyHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((MyHolder) holder).tv.setText(dataList.get(position));
            }

            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(PickViewActivity.this, "点击："
                        + dataList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(PickViewActivity.this, "删除："
                        + dataList.get(position), Toast.LENGTH_SHORT).show();
                myAdapter.remove(position);
            }
        }));

    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 47; i++) {
            dataList.add(String.valueOf(i));
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv = null;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }
    }
}


