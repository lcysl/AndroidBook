package com.uestc.lcy.androidbook.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseFragment;
import com.uestc.lcy.androidbook.config.UserConfig;
import com.uestc.lcy.androidbook.model.ArticleListBean;
import com.uestc.lcy.androidbook.model.BannerBean;
import com.uestc.lcy.androidbook.modules.MainActivity;
import com.uestc.lcy.androidbook.modules.collection.view.CollectionView;
import com.uestc.lcy.androidbook.modules.home.article_content.ArticleContentActivity;
import com.uestc.lcy.androidbook.modules.home.article_list.adapter.ArticleListAdapter;
import com.uestc.lcy.androidbook.modules.home.article_list.imageloader.GlideImageLoader;
import com.uestc.lcy.androidbook.modules.home.article_list.presenter.ArticleListPresenter;
import com.uestc.lcy.androidbook.modules.home.article_list.view.ArticleListView;
import com.uestc.lcy.androidbook.modules.login.LoginActivity;
import com.uestc.lcy.androidbook.views.ArticleListRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018\4\10 0010.
 */

public class HomeFragment extends BaseFragment<ArticleListPresenter> implements ArticleListView,
        ArticleListRecyclerView.OnLoadMoreListener, ArticleListAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener, OnBannerListener, CollectionView {

    /*context*/
    private MainActivity mActivity;
    /*定义文章列表的页码数*/
    private int mPage = 0;
    /*列表刷新*/
    private Handler mHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    /*RecyclerView相关*/
    private ArticleListRecyclerView mRecyclerView;
    private ArticleListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    /*首页banner*/
    private Banner mBanner;
    private List<BannerBean.DataBean> mBannerData;
    /*列表项的内容*/
    private List<ArticleListBean.DataBean.DatasBean> mDatas;

    private int itemPosition; // 标识当前点击的item位置


    /**
     * 实现父类的抽象方法，相当于OnCreateView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        setListener();
        return view;
    }

    /**
     * 实现父类的抽象方法，初始化Presenter
     */
    @Override
    protected void initPresenter() {
        mPresenter = new ArticleListPresenter();
        mPresenter.attachView(this);
    }

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.srl_article_list);
        mRecyclerView = view.findViewById(R.id.rv_article_list);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //调用P层的网络请求方法
        mPresenter.loadArticleList(mPage);
    }

    /**
     * 设置事件监听
     */
    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setOnLoadMoreListener(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    //将网络请求得到的数据显示到列表中
    @Override
    public void onLoadArticleListSuccess(ArticleListBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(mActivity, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mDatas = new ArrayList<>();
            mDatas.addAll(bean.getData().getDatas());
            Log.e("--------A",bean.getData().getDatas().toString());
            mAdapter = new ArticleListAdapter(mDatas);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
        //首页列表请求成功后再请求banner
        mPresenter.loadBanner();
    }

    @Override
    public void onLoadArticleListError() {
        Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreArticleListSuccess(ArticleListBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(mActivity, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mDatas.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 网络请求成功后，将图片设置给banner
     * @param bean
     */
    @Override
    public void onLoadBannerSuccess(BannerBean bean) {
        mBannerData = bean.getData();
        //渲染header的布局
        View header = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, mRecyclerView, false);
        mBanner = header.findViewById(R.id.banner);
        //配置banner
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(getImages());
        mBanner.start();
        //把hearView设置给adapter
        mAdapter.setHeaderView(header);
        //刷新adapter
        mAdapter.notifyDataSetChanged();

        mBanner.setOnBannerListener(this);
    }

    private List<String> getImages() {
        List<String> images = new ArrayList<>();
        for (BannerBean.DataBean data : mBannerData) {
            images.add(data.getImagePath());
        }
        return images;
    }

    @Override
    public void onLoadBannerError() {
        Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLoadMore() {
        mPage++;
        mPresenter.loadArticleList(mPage);
    }


    @Override
    public void onItemClick(View view, int position, List<ArticleListBean.DataBean.DatasBean> datas) {
        //将网址信息传递给ArticleContentActivity进行加载
        ArticleListBean.DataBean.DatasBean bean = datas.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("url", bean.getLink());
        bundle.putString("title", bean.getTitle());
        Intent intent = new Intent(mActivity, ArticleContentActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onCollectionClick(int position) {
        boolean isLogin = UserConfig.getInstance().getString("userInfo", null) != null ? true : false;
        if(isLogin) {
            itemPosition = position;
            ArticleListBean.DataBean.DatasBean datasBean = mDatas.get(position);
            if(datasBean.isCollect()) {
                mPresenter.cancelCollection(datasBean.getId());
            } else {
                mPresenter.collection(datasBean.getTitle(), datasBean.getAuthor(), datasBean.getLink());
            }
        } else {
            startActivity(new Intent(mActivity, LoginActivity.class));
        }
    }

    @Override
    public void OnBannerClick(int position) {
        BannerBean.DataBean data = mBannerData.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("url", data.getUrl());
        bundle.putString("title", data.getTitle());
        Intent intent = new Intent(mActivity, ArticleContentActivity.class);
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

    @Override
    public void onCollectionSuccess() {
        mDatas.get(itemPosition).setCollect(true);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(mActivity, "已收藏", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCollecionError() {
        Toast.makeText(mActivity, "收藏失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelCollectionSuccess() {
        mDatas.get(itemPosition).setCollect(false);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelCollecionError() {

    }
}
