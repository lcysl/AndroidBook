package com.uestc.lcy.androidbook.modules.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.config.AppConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018\4\9 0009.
 */

public class GuideActivity extends FragmentActivity {
    private ViewPager vpGuide;
    private LinearLayout llIndicator;

    private List<Fragment> fragments = new ArrayList<>();
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initFragment();
        initData();
        initIndicator();
        initListener();

        //将sp里面guideFirstShow对应的值设置为true，说明引导页显示过了
        AppConfig appConfig = AppConfig.getInstance();
        appConfig.setBoolean("guideShow", true);
    }


    private void initView() {
        vpGuide = findViewById(R.id.vp_guide);
        llIndicator = findViewById(R.id.ll_guide_indicator);
    }

    private void initFragment() {
        for (int i = 0; i < 3; i++) {
            GuideContentFragment guideContentFragment = new GuideContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            guideContentFragment.setArguments(bundle);
            fragments.add(guideContentFragment);
        }
    }

    private void initData() {
        adapter = new GuidePageAdapter(getSupportFragmentManager(), fragments);
        vpGuide.setAdapter(adapter);
    }

    /**
     * 指示条的初始化绘制
     */
    private void initIndicator() {
        //这一部分不太懂？？
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10f, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        lp.rightMargin = 2 * width;

        for(int i = 0; i < fragments.size(); i++) {
            View view = new View(this);
            view.setId(i);
            view.setBackgroundResource(i == 0 ?
                    R.drawable.guide_indicator_selected:R.drawable.guide_indicator_normal);
            view.setLayoutParams(lp);
            llIndicator.addView(view, i);
        }
    }

    private void initListener() {
        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for(int i = 0; i < fragments.size(); i++) {
                    llIndicator.getChildAt(i).setBackgroundResource(position == i ?
                            R.drawable.guide_indicator_selected:R.drawable.guide_indicator_normal);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
