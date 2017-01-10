package com.yangbin.indicatordiy;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<View> mViews = new ArrayList<>();
    private Indicator mIndicator;
    private int[] imagers = {R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setOnPageChangeListener(new MyChangeListener());
        mIndicator = (Indicator) findViewById(R.id.indicator);
    }

    private class MyChangeListener implements ViewPager.OnPageChangeListener {
        //ViewPager的滚动的

        /***
         * @param position
         * @param positionOffset       偏移的百分比
         * @param positionOffsetPixels 偏移量
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e("onPageScrolled", "positionOffset<< " + positionOffset + "  positionOffsetPixels<<<" + positionOffsetPixels);
            mIndicator.setOffset(position, positionOffset);
        }

        //ViewPager选中
        @Override
        public void onPageSelected(int position) {
        }

        //ViewPager滑动状态改变
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            View inflater = getLayoutInflater().inflate(R.layout.pager_item, null);
            ImageView mImagView = (ImageView) inflater.findViewById(R.id.iv_guide);
            mImagView.setImageResource(imagers[i]);
            mViews.add(inflater);
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            position %= 4;
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= 4;
            View view = mViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
