package com.uestc.lcy.androidbook.modules.project.activity;

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
import com.uestc.lcy.androidbook.model.ProjectListBean;
import com.uestc.lcy.androidbook.modules.home.article_content.ArticleContentActivity;
import com.uestc.lcy.androidbook.modules.project.adapter.ProjectListAdapter;
import com.uestc.lcy.androidbook.modules.project.presenter.ProjectListPresenter;
import com.uestc.lcy.androidbook.modules.project.view.ProjectListView;
import com.uestc.lcy.androidbook.views.ArticleListRecyclerView;

import java.util.List;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public class ProjectListActivity extends BaseActivity<ProjectListPresenter> implements
        ProjectListView, ArticleListRecyclerView.OnLoadMoreListener, ProjectListAdapter.OnItemClickListener{

    /*标题栏*/
    private Button mBackBtn;
    private TextView mTopBarTitle;
    /*页码*/
    private int mPage = 1;
    /*RecyclerView相关*/
    private ArticleListRecyclerView mRecyclerView;
    private ProjectListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<ProjectListBean.DataBean.DatasBean> mDatas;


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
        mPresenter = new ProjectListPresenter();
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

        mRecyclerView = findViewById(R.id.rv_project_list);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //更新标题栏
        String name = bundle.getString("name");
        mTopBarTitle.setText(name);
        //获取页码和上一级的id，调用P层的网络请求
        int cid = bundle.getInt("id");
        mPresenter.loadProjectList(mPage, cid);
    }

    private void setListener() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mRecyclerView.setOnLoadMoreListener(this);
    }

    @Override
    public void onLoadProjectListSuccess(ProjectListBean bean) {
//        Toast.makeText(this, "网络请求成功", Toast.LENGTH_SHORT).show();
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mDatas = bean.getData().getDatas();
            mAdapter = new ProjectListAdapter(mDatas, this);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoadProjectListError() {
        Toast.makeText(this, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreProjectListSuccess(ProjectListBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mDatas.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onLoadMore() {
        mPage++;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //获取页码和上一级的id，调用P层的网络请求
        int cid = bundle.getInt("id");
        mPresenter.loadProjectList(mPage, cid);
    }

    @Override
    public void onItemClick(View view, int position, List<ProjectListBean.DataBean.DatasBean> datas) {
        ProjectListBean.DataBean.DatasBean bean = datas.get(position);
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
