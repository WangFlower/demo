package com.example.momo.myapplication.demo.diandian;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by MOMO on 16/12/19.
 */

public class CardLayoutManager extends RecyclerView.LayoutManager {

    private static final int ITEM_COUNT = 4;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                .LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);

//        if(getChildCount()>=ITEM_COUNT){
//
//            for ()
//
//        }



        super.onLayoutChildren(recycler, state);
    }
}
