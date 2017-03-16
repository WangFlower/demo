package com.example.momo.myapplication.gift;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOMO on 17/2/13.
 */

public class GiftViewPager extends ViewPager {


    private List<String> data ;


    public GiftViewPager(Context context) {
        super(context);
        init();
    }

    public GiftViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        data  = new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add(""+i);
        }
    }


}
