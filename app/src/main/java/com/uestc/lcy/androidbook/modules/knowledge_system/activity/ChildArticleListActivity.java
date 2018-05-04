package com.uestc.lcy.androidbook.modules.knowledge_system.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.model.ChildArticleListBean;
import com.uestc.lcy.androidbook.modules.home.article_content.ArticleContentActivity;
import com.uestc.lcy.androidbook.modules.knowledge_system.adapter.ChildArticleListAdapter;
import com.uestc.lcy.androidbook.modules.knowledge_system.presenter.ChildArticleListPresenter;
import com.uestc.lcy.androidbook.modules.knowledge_system.view.ChildArticleListView;

import java.util.List;

/**
 * Created by lcy on 2018\5\1 0001.
 */

public class ChildArticleListActivity extends BaseActivity<ChildArticleListPresenter> implements
        ChildArticleListView, ChildArticleListAdapter.OnItemClickListener{
    /*标题栏*/
    private Button mBackBtn;
    private TextView mTopBarTitle;
    /*列表刷新*/
    private Handler mHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    /*传值相关*/
    private Intent mIntent;
    private Bundle mBundle;
    /*RecyclerView相关*/
    private RecyclerView mRecyclerView;
    private ChildArticleListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    /*列表项的内容*/
    private List<ChildArticleListBean.DataBean.DatasBean> mDatas;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_child_article_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        setListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ChildArticleListPresenter();
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
        mSwipeRefreshLayout = findViewById(R.id.srl_child_article_list);
        mRecyclerView = findViewById(R.id.rv_child_article_list);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initData() {
        mIntent = getIntent();
        mBundle = mIntent.getExtras();
        //更新标题栏
        String childName = mBundle.getString("childrenName");
        mTopBarTitle.setText(childName);
        //获取一级id和二级id，调用P层的网络请求
        int firstId = mBundle.getInt("firstId");
        int childrenId = mBundle.getInt("childrenId");
        mPresenter.loadChildArticleList(firstId, childrenId);
    }

    private void setListener() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    /**
     * 请求成功后更新列表项
     * @param bean
     */
    @Override
    public void onLoadChildArticleListSuccess(ChildArticleListBean bean) {
//        Toast.makeText(this, "网络请求成功", Toast.LENGTH_SHORT).show();
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mDatas = bean.getData().getDatas();
            mAdapter = new ChildArticleListAdapter(mDatas);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoadChildArticleListError() {
        Toast.makeText(this, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position, List<ChildArticleListBean.DataBean.DatasBean> datas) {
        ChildArticleListBean.DataBean.DatasBean bean = datas.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("url", bean.getLink());
        bundle.putString("title", bean.getTitle());
        Intent intent = new Intent(this, ArticleContentActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
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
