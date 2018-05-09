package com.uestc.lcy.androidbook.modules.home.article_content;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;

/**
 * Created by lcy on 2018\4\26 0026.
 */

public class ArticleContentActivity extends BaseActivity {

    private Button mBackBtn;
    private TextView mTopBarTitle;
    private WebView mArticleContentWv;
    private WebSettings mWebSettings;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_article_content;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        setListener();
    }


    @Override
    protected void initPresenter() {

    }

    private void initView() {
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
        mTopBarTitle = findViewById(R.id.tv_top_bar2_title);
        mArticleContentWv = findViewById(R.id.wv_article_content);
        mWebSettings = mArticleContentWv.getSettings();
    }


    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("url");
        //设置是否使用WebView内置的放大机制
        mWebSettings.setBuiltInZoomControls(true);
        //设置是否使用WebView推荐使用的窗口
        mWebSettings.setUseWideViewPort(true);
        //设置WebView加载页面的模式
        mWebSettings.setLoadWithOverviewMode(true);
        mArticleContentWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mArticleContentWv.loadUrl(url);

        String title = bundle.getString("title");
        mTopBarTitle.setText(title);
        mTopBarTitle.setTextSize(18f);
    }


    private void setListener() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
