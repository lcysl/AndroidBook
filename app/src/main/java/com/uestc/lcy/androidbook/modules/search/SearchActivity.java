package com.uestc.lcy.androidbook.modules.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.modules.search.activity.SearchListActivity;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mBackIv;
    private EditText mSearchEt;
    private ImageView mEmptyIv;
    private ImageView mSearchIv;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        setListener();
    }

    @Override
    protected void initPresenter() {

    }

    public void initView() {
        mBackIv = findViewById(R.id.iv_back);
        mSearchEt = findViewById(R.id.et_search);
        mEmptyIv = findViewById(R.id.iv_empty);
        mSearchIv = findViewById(R.id.iv_search);
    }

    private void setListener() {
        mBackIv.setOnClickListener(this);
        mSearchIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_empty:
                mSearchEt.setText("");
                break;
            case R.id.iv_search:
                //将输入框的值传给SearchListActivity进行网络请求
                Intent intent = new Intent(this, SearchListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", mSearchEt.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
