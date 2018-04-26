package com.uestc.lcy.androidbook.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.uestc.lcy.androidbook.modules.home.articlecontent.ArticleContentActivity;
import com.uestc.lcy.androidbook.modules.home.articlelist.adapter.ArticleListAdapter;
import com.uestc.lcy.androidbook.modules.home.articlelist.presenter.ArticleListPresenter;
import com.uestc.lcy.androidbook.modules.home.articlelist.view.ArticleListView;
import com.uestc.lcy.androidbook.views.ArticleListRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class HomeFragment extends BaseFragment<ArticleListPresenter> implements ArticleListView,
        ArticleListRecyclerView.OnLoadMoreListener, ArticleListAdapter.OnItemClickListener{

    private MainActivity mActivity;

    private int mPage = 0;

    private ArticleListRecyclerView mRecyclerView;
    private ArticleListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<ArticleListBean.DataBean.DatasBean> mDatas;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        setListener();
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

    private void setListener() {
        mRecyclerView.setOnLoadMoreListener(this);

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
        mDatas = new ArrayList<>(bean.getData().getDatas());
        mAdapter = new ArticleListAdapter(mDatas);

        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoadArticleListError() {
        Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreArticleListSuccess(ArticleListBean bean) {
        mDatas.addAll(bean.getData().getDatas());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        super.showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        super.hideLoadingDialog();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        mPresenter.loadArticleList(mPage);
    }


    @Override
    public void onItemClick(View view, int position, ArticleListBean.DataBean.DatasBean bean) {
        //将网址信息传递给ArticleContentActivity进行加载
        Bundle bundle = new Bundle();
        bundle.putString("url", bean.getLink());
        bundle.putString("title", bean.getTitle());
        Intent intent = new Intent(mActivity, ArticleContentActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
