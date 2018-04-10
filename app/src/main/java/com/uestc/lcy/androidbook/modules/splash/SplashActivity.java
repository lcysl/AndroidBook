package com.uestc.lcy.androidbook.modules.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.config.AppConfig;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.guide.GuideActivity;

/**
 * Created by lcy on 2018\4\9 0009.
 */

public class SplashActivity extends Activity {

    private Button btnSkip;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DelayStart();
        initView();
        setListener();
    }

    /**
     * 初始化View
     */
    private void initView() {
        btnSkip = findViewById(R.id.btn_splash);
    }

    private void setListener() {
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable);
                startGuideOrMain();
            }
        });
    }


    private void DelayStart() {
        mHandler.postDelayed(mRunnable, 5000);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            startGuideOrMain();
        }
    };

    private void startGuideOrMain() {
        // 从 sharedPreferences 里取出值，判断值是否为true，
        // 为true，不跳转引导页，跳转到主页面，否则跳转
        AppConfig appConfig = AppConfig.getInstance();
        Boolean isGuideShow = appConfig.getBoolean("guideShow", false);
        if (isGuideShow) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
        }
    }
}
