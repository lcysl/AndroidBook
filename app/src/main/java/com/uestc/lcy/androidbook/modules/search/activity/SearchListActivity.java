package com.uestc.lcy.androidbook.modules.search.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.model.SearchBean;
import com.uestc.lcy.androidbook.modules.search.adapter.SearchListAdapter;
import com.uestc.lcy.androidbook.modules.search.presenter.SearchListPresenter;
import com.uestc.lcy.androidbook.modules.search.view.SearchListView;
import com.uestc.lcy.androidbook.views.ArticleListRecyclerView;

import java.util.List;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public class SearchListActivity extends BaseActivity<SearchListPresenter> implements SearchListView{

    /*标题栏*/
    private Button mBackBtn;
    private TextView mTopBarTitle;
    /*页码*/
    private int mPage = 0;
    /*RecyclerView相关*/
    private ArticleListRecyclerView mRecyclerView;
    private SearchListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<SearchBean.DataBean.DatasBean> mDatas;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_search_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        setListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new SearchListPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void initView() {
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
        mTopBarTitle = findViewById(R.id.tv_top_bar2_title);

        mRecyclerView = findViewById(R.id.rv_search_list);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //更新标题栏
        String key = bundle.getString("key");
        mTopBarTitle.setText("搜索关键词：" + key);
        mTopBarTitle.setTextSize(15f);
        //调用P层的网络请求方法
        mPresenter.loadSearchList(mPage, key);
    }

    private void setListener() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onLoadSearchListSuccess(SearchBean bean) {
//        Toast.makeText(this, "网络请求成功", Toast.LENGTH_SHORT).show();
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            if (bean.getData() != null) {
                mDatas = bean.getData().getDatas();
                mAdapter = new SearchListAdapter(mDatas, this);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }

    @Override
    public void onLoadSearchListError() {
        Toast.makeText(this, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        super.showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        super.hideLoadingDialog();
    }
}
