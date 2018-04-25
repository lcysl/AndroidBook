package com.uestc.lcy.androidbook.modules.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseFragment;
import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.home.adapter.ArticleListAdapter;
import com.uestc.lcy.androidbook.modules.home.presenter.ArticleListPresenter;
import com.uestc.lcy.androidbook.modules.home.view.ArticleListView;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class HomeFragment extends BaseFragment<ArticleListPresenter> implements ArticleListView {

    private MainActivity mActivity;

    private int mPage = 0;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_article_list);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void initData() {
        //调用P层的网络请求方法
        mPresenter.loadArticleList(mPage);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ArticleListPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    //将网络请求得到的数据显示到列表中
    @Override
    public void onLoadArticleListSuccess(ArticleListBean bean) {
        mAdapter = new ArticleListAdapter(bean.getData().getDatas());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoadArticleListError() {
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
