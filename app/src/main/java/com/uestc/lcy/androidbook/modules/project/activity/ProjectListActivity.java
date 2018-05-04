package com.uestc.lcy.androidbook.modules.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public class ProjectListActivity extends BaseActivity {

    /*标题栏*/
    private Button mBackBtn;
    private TextView mTopBarTitle;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_project_list;
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

    public void initView() {
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
        mTopBarTitle = findViewById(R.id.tv_top_bar2_title);
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        mTopBarTitle.setText(name);
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
