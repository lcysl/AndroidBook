package com.uestc.lcy.androidbook.modules.sort;

import android.content.Context;
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
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.home.articlelist.adapter.ArticleListAdapter;
import com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.adapter.KnowledgeSystemAdapter;
import com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.presenter.KnowledgeSystemPresenter;
import com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.view.KnoledgeSystemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class SortFragment extends BaseFragment<KnowledgeSystemPresenter> implements KnoledgeSystemView, SwipeRefreshLayout.OnRefreshListener{

    /*context*/
    private MainActivity mActivity;
    /*列表刷新*/
    private Handler mHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    /*RecyclerView相关*/
    private RecyclerView mRecyclerView;
    private KnowledgeSystemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    /*列表项的内容*/
    private List<KnowledgeSystemBean.DataBean> mData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        initView(view);
        initData();
        setListener();
        return view;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new KnowledgeSystemPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    /**
     * 初始化组件
     * @param view
     */
    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.srl_knowledge_system);
        mRecyclerView = view.findViewById(R.id.rv_knowledge_system);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initData() {
        //调用P层的网络请求方法
        mPresenter.loadKnoledgeSystem();
    }

    /**
     * 设置事件监听
     */
    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

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

    /**
     * 将网络请求得到的数据显示到列表中
     * @param bean
     */
    @Override
    public void onLoadKnowledgeSystemSuccess(KnowledgeSystemBean bean) {
        Toast.makeText(mActivity, "网络请求成功", Toast.LENGTH_SHORT).show();
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(mActivity, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mData = bean.getData();
            mAdapter = new KnowledgeSystemAdapter(mData);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoadKnowledgeSystemError() {
        Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
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
