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

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;
import com.uestc.lcy.androidbook.modules.knowledge_system.adapter.ChildSystemAdapter;

import java.util.List;

/**
 * 显示二级目录的列表
 * Created by lcy on 2018\5\1 0001.
 */

public class ChildSystemActivity extends BaseActivity implements ChildSystemAdapter.OnItemClickListener{

    /*标题栏*/
    private Button mBackBtn;
    private TextView mTopBarTitle;
    /*列表刷新*/
    private Handler mHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    /*RecyclerView相关*/
    private RecyclerView mRecyclerView;
    private ChildSystemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    /*列表项的内容*/
    private List<KnowledgeSystemBean.DataBean.ChildrenBean> mChildren;
    /*传值相关*/
    private Intent mIntent;
    private Bundle mBundle;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_child_system;
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
        mSwipeRefreshLayout = findViewById(R.id.srl_child_system);
        mRecyclerView = findViewById(R.id.rv_child_system);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initData() {
        mIntent = getIntent();
        mBundle = mIntent.getExtras();
        //更新标题栏
        String firstName = mBundle.getString("firstName");
        mTopBarTitle.setText(firstName);
        //加载列表项
        mChildren = (List<KnowledgeSystemBean.DataBean.ChildrenBean>) mBundle.getSerializable("children");
        mAdapter = new ChildSystemAdapter(mChildren);
        mAdapter.setmOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
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

    @Override
    public void onItemClick(View view, int position, List<KnowledgeSystemBean.DataBean.ChildrenBean> children) {
        int firstId = mBundle.getInt("firstId");
        int childrenId = children.get(position).getId();
        String childrenName = children.get(position).getName();
        //将一级id、二级cid，二级标题传给ChildArticleListActivity
        Bundle bundle = new Bundle();
        bundle.putInt("firstId", firstId);
        bundle.putInt("childrenId", childrenId);
        bundle.putString("childrenName", childrenName);
        Intent intent = new Intent(this, ChildArticleListActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
