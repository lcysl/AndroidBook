package com.uestc.lcy.androidbook.modules.collection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.model.FeedArticleData;
import com.uestc.lcy.androidbook.model.ProjectListBean;
import com.uestc.lcy.androidbook.modules.collection.adapter.CollectionListAdapter;
import com.uestc.lcy.androidbook.modules.collection.presenter.CollectionPresenter;
import com.uestc.lcy.androidbook.modules.collection.view.CollectionListView;
import com.uestc.lcy.androidbook.views.ArticleListRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018\6\16 0016.
 */

public class CollectionActivity extends BaseActivity<CollectionPresenter> implements CollectionListView{

    /*标题栏*/
    private Button mBackBtn;
    private TextView mTopBarTitle;
    /*页码*/
    private int mPage = 0;
    /*RecyclerView相关*/
    private ArticleListRecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CollectionListAdapter mAdapter;
    private List<ProjectListBean.DataBean.DatasBean> mDatas;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        setListener();
    }

    public void initView() {
        mBackBtn = findViewById(R.id.btn_top_bar2_return);
        mTopBarTitle = findViewById(R.id.tv_top_bar2_title);
        mRecyclerView = findViewById(R.id.rv_collection_list);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void initData() {
        mTopBarTitle.setText("我的收藏");
        mPresenter.loadCollectionList(mPage);
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
    protected void initPresenter() {
        mPresenter = new CollectionPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onLoadCollectionListSuccess(List<FeedArticleData> collectionList) {
        mAdapter = new CollectionListAdapter(collectionList, this);
        mRecyclerView.setAdapter(mAdapter);
        Log.e("-----------B",collectionList.size()+"");
    }

    @Override
    public void onLoadCollectionListError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
