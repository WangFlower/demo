package com.example.momo.myapplication.demo.others;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;

import com.example.momo.myapplication.BaseActivity;
import com.example.momo.myapplication.R;
import com.example.momo.myapplication.utils.UIUtils;

/**
 * Created by wang.renguang on 2018/7/6.
 */
public class ViewDemoActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_demo);

        ViewStub viewStub1 = findViewById(R.id.profile_account_fortune_layout_vs);
        View view1 = viewStub1.inflate();
        viewStub1.setVisibility(View.VISIBLE);
        ImageView imageView1 = findViewById(R.id.profile_account_fortune_icon);
        ImageView imageView11 = view1.findViewById(R.id.profile_account_fortune_icon);
        updateLayout(imageView1);
        imageView1.setBackgroundResource(R.drawable.oval_7);

        Log.i("wangrenguang","imageView1:"+imageView1);
        Log.i("wangrenguang","imageView11:"+imageView11);

        ViewStub viewStub2 = findViewById(R.id.profile_account_vip_layout_vs);
        View view2 = viewStub2.inflate();
        viewStub2.setVisibility(View.VISIBLE);
        ImageView imageView2 = findViewById(R.id.profile_account_fortune_icon);
        ImageView imageView22 = view2.findViewById(R.id.profile_account_fortune_icon);
        updateLayout(imageView2);
        imageView2.setBackgroundResource(R.drawable.ball);
        Log.i("wangrenguang","imageView2:"+imageView2);
        Log.i("wangrenguang","imageView22:"+imageView22);

        ViewStub viewStub3 = findViewById(R.id.profile_account_grow_layout_vs);
        View view3 = viewStub3.inflate();
        view3.setVisibility(View.VISIBLE);
        ImageView imageView3 = findViewById(R.id.profile_account_fortune_icon);
        ImageView imageView33 = view3.findViewById(R.id.profile_account_fortune_icon);
        updateLayout(imageView3);
        imageView3.setBackgroundResource(R.drawable.oval_6);
        Log.i("wangrenguang","imageView3:"+imageView3);
        Log.i("wangrenguang","imageView33:"+imageView33);


        Log.i("wangrenguang","----- "+(imageView1==imageView11));
        Log.i("wangrenguang","----- "+(imageView2==imageView22));
        Log.i("wangrenguang","----- "+(imageView3==imageView33));
    }


    public static void updateLayout(View v){

        int width = UIUtils.getScreenWidth()/4;
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if(layoutParams == null){
            return;
        }
        layoutParams.height = width;
        layoutParams.width = width;
        v.setLayoutParams(layoutParams);
        v.requestLayout();
    }

}
