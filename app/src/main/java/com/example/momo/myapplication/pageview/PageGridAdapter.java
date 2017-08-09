package com.example.momo.myapplication.pageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.momo.myapplication.R;

/**
 * Created by sam on 2017/8/1.
 */

public class PageGridAdapter extends BaseAdapter {

    private Context mContext;

    public PageGridAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.page_grid_adapter_layout, parent, false);
    }
}
