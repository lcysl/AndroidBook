package com.uestc.lcy.androidbook.modules.knowledge_system;

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
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.knowledge_system.activity.ChildSystemActivity;
import com.uestc.lcy.androidbook.modules.knowledge_system.adapter.KnowledgeSystemAdapter;
import com.uestc.lcy.androidbook.modules.knowledge_system.presenter.KnowledgeSystemPresenter;
import com.uestc.lcy.androidbook.modules.knowledge_system.view.KnowledgeSystemView;

import java.io.Serializable;
import java.util.List;

/**
 * 显示知识体系的列表，包括一级题目和一级题目下的二级题目
 * Created by lcy on 2018\4\10 0010.
 */

public class KnowledgeSystemFragment extends BaseFragment<KnowledgeSystemPresenter> implements
        KnowledgeSystemView, SwipeRefreshLayout.OnRefreshListener, KnowledgeSystemAdapter.OnItemClickListener{

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
        View view = inflater.inflate(R.layout.fragment_knowledge_system, container, false);
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
        mPresenter.loadKnowledgeSystem();
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
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(mActivity, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mData = bean.getData();
            mAdapter = new KnowledgeSystemAdapter(mData);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoadKnowledgeSystemError() {
        Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击列表项，跳转到ChildSystemActivity
     * @param view
     * @param position
     * @param data
     */
    @Override
    public void onItemClick(View view, int position, List<KnowledgeSystemBean.DataBean> data) {
        //获取到一级标题的题目、二级标题的集合，传给ChildSystemActivity
        KnowledgeSystemBean.DataBean bean = data.get(position);
        String firstName = bean.getName();
        List<KnowledgeSystemBean.DataBean.ChildrenBean> children = bean.getChildren();
        int firstId = bean.getParentChapterId();
        Bundle bundle = new Bundle();
        bundle.putString("firstName", firstName);
        bundle.putSerializable("children", (Serializable) children);
        bundle.putInt("firstId", firstId);
        Intent intent = new Intent(mActivity, ChildSystemActivity.class);
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
