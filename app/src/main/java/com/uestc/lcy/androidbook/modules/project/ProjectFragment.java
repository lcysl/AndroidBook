package com.uestc.lcy.androidbook.modules.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseFragment;
import com.uestc.lcy.androidbook.model.ProjectSortBean;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.project.activity.ProjectListActivity;
import com.uestc.lcy.androidbook.modules.project.adapter.ProjectSortAdapter;
import com.uestc.lcy.androidbook.modules.project.presenter.ProjectSortPresenter;
import com.uestc.lcy.androidbook.modules.project.view.ProjectSortView;

import java.util.List;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public class ProjectFragment extends BaseFragment<ProjectSortPresenter> implements
        ProjectSortView, ProjectSortAdapter.OnItemClickListener{

    /*context*/
    private MainActivity mActivity;
    /*列表刷新*/
    private Handler mHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    /*RecyclerView相关*/
    private RecyclerView mRecyclerView;
    private ProjectSortAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    /*列表项的内容*/
    private List<ProjectSortBean.DataBean> mData;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        initView(view);
        initData();
        setListener();
        return view;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ProjectSortPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.srl_project_sort);
        mRecyclerView = view.findViewById(R.id.rv_project_sort);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initData() {
        //调用P层的网络请求方法
        mPresenter.loadProjectSort();
    }

    private void setListener() {
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
    public void onLoadProjectSortSuccess(ProjectSortBean bean) {
//        Toast.makeText(mActivity, "网络请求成功", Toast.LENGTH_SHORT).show();
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(mActivity, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mData = bean.getData();
            mAdapter = new ProjectSortAdapter(mData, mActivity);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoadProjectSortError() {
        Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 列表项的点击事件
     * @param view
     * @param position
     * @param data
     */
    @Override
    public void onItemClick(View view, int position, List<ProjectSortBean.DataBean> data) {
        //获取项目分类的名称和id，传给ProjectSortActivity
        ProjectSortBean.DataBean bean = data.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("name", bean.getName());
        bundle.putInt("id", bean.getId());
        Intent intent = new Intent(mActivity, ProjectListActivity.class);
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
