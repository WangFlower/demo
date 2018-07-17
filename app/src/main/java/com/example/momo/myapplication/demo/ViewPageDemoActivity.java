package com.example.momo.myapplication.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.utils.UIUtils;

/**
 * Created by wang.renguang on 2018/6/21.
 */
public class ViewPageDemoActivity extends Activity{

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private viewPagerAdapter mViewPageAdapter;


    private View view;
    private View line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage);
        mTablayout= (TabLayout) findViewById(R.id.tabLayout);
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        mViewPageAdapter = new viewPagerAdapter();
        mViewPager.setAdapter(mViewPageAdapter);
        view = findViewById(R.id.ffffffff);
        line = findViewById(R.id.line);

        setmTablayout();
        ff(0);
    }

    private void setmTablayout()
    {
        mTablayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            View tab = ((ViewGroup) mTablayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, UIUtils.getPixels(6), 0);
            p.width = UIUtils.getPixels(6);
            p.height = UIUtils.getPixels(6);
            tab.requestLayout();
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("wangrenguang","positionOffset "+positionOffset);
                Log.i("wangrenguang","position "+position);
                Log.i("wangrenguang","positionOffsetPixels "+positionOffsetPixels);
                ff(positionOffset+position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);

    }


    private void ff(float positionOffset){
        FrameLayout.LayoutParams params ;
        if(view.getLayoutParams()!=null){
            params = (FrameLayout.LayoutParams) view.getLayoutParams();
        } else {
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIUtils.getPixels(55));
        }
        params.leftMargin = (int) (UIUtils.getScreenWidth()*(1-positionOffset));
        view.setLayoutParams(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(line.getLayoutParams()!=null){
            ViewGroup.LayoutParams v = line.getLayoutParams();
            v.width = UIUtils.getScreenWidth()*2;
            line.setLayoutParams(v);
        }
    }

    class viewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView(((ImageView) object));

        }


        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(ViewPageDemoActivity.this);
            imageView.setImageResource(R.drawable.ball);
            container.addView(imageView);
            return imageView;
        }



    }
}
