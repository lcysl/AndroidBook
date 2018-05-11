package com.uestc.lcy.androidbook.modules.search;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.config.AppConfig;
import com.uestc.lcy.androidbook.model.HotKeyBean;
import com.uestc.lcy.androidbook.model.SearchBean;
import com.uestc.lcy.androidbook.modules.home.article_content.ArticleContentActivity;
import com.uestc.lcy.androidbook.modules.project.adapter.ProjectListAdapter;
import com.uestc.lcy.androidbook.modules.search.adapter.SearchListAdapter;
import com.uestc.lcy.androidbook.modules.search.presenter.SearchListPresenter;
import com.uestc.lcy.androidbook.modules.search.view.SearchListView;
import com.uestc.lcy.androidbook.views.ArticleListRecyclerView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018\5\6 0006.
 */

public class SearchActivity extends BaseActivity<SearchListPresenter> implements
        View.OnClickListener, SearchListView, ArticleListRecyclerView.OnLoadMoreListener,
        SearchListAdapter.OnItemClickListener{

    /*标题栏相关*/
    private ImageView mBackIv;
    private EditText mSearchEt;
    private ImageView mEmptyIv;
    private ImageView mSearchIv;
    /*历史搜索相关*/
    private LinearLayout mSearchHistoryLl;
    private TagFlowLayout mSearchHistoryTfl;
    private TextView mClearSearchHistoryTv;
    /*搜索热词相关*/
    private TagFlowLayout mHotKeyTfl;
    /*页码*/
    private int mPage = 0;
    /*RecyclerView相关*/
    private ArticleListRecyclerView mRecyclerView;
    private SearchListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<SearchBean.DataBean.DatasBean> mDatas;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        setListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new SearchListPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void initView() {
        mBackIv = findViewById(R.id.iv_back);
        mSearchEt = findViewById(R.id.et_search);
        mEmptyIv = findViewById(R.id.iv_empty);
        mSearchIv = findViewById(R.id.iv_search);
        mSearchHistoryLl = findViewById(R.id.ll_search_history);
        mSearchHistoryLl.bringToFront();
        mSearchHistoryTfl = findViewById(R.id.tfl_search_history);
        mClearSearchHistoryTv = findViewById(R.id.tv_clear_search_history);
        mHotKeyTfl = findViewById(R.id.tfl_hot_key);

        mRecyclerView = findViewById(R.id.rv_search_list);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void initData() {
        //从sp中取出集合数据，更新到TagFlowLayout
        List<String> keyList = AppConfig.getInstance().getList("keyList");
        mSearchHistoryTfl.setAdapter(new TagAdapter<String>(keyList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, mSearchHistoryTfl, false);
                tv.setText(s);
                return tv;
            }
        });
        //调用P层的网络请求加载搜索热词
        mPresenter.loadHotKey();
    }

    private void setListener() {
        //返回键、清空搜索框按钮、搜索按钮的点击事件
        mBackIv.setOnClickListener(this);
        mSearchIv.setOnClickListener(this);
        mEmptyIv.setOnClickListener(this);
        //软键盘右下角按键的事件监听
        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String key = mSearchEt.getText().toString();
                //将关键字存入sp,用于更新历史搜索
                List<String> keyList = AppConfig.getInstance().getList("keyList");
                if (!keyList.contains(key)) {
                    keyList.add(key);
                    AppConfig.getInstance().setList("keyList", keyList);
                }
                //调用P层的网络请求
                mPresenter.loadSearchList(mPage, key);
                return true;
            }
        });
        //列表加载更多的事件监听
        mRecyclerView.setOnLoadMoreListener(this);
        //FlowLayout标签的事件监听
        mSearchHistoryTfl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                List<String> keyList = AppConfig.getInstance().getList("keyList");
                String key = keyList.get(position);
                mPresenter.loadSearchList(mPage, key);
                return true;
            }
        });
        //清空搜索历史的事件监听
        mClearSearchHistoryTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        List<String> keyList;
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_empty:
                mSearchEt.setText("");
                break;
            case R.id.iv_search:
                //将输入框的值传给P层进行网络请求
                String key = mSearchEt.getText().toString();
                //将关键字存入sp,用于更新历史搜索
                keyList = AppConfig.getInstance().getList("keyList");
                if (!keyList.contains(key)) {
                    keyList.add(key);
                    AppConfig.getInstance().setList("keyList", keyList);
                }
                //调用P层的网络请求
                mPresenter.loadSearchList(mPage, key);
                break;
            case R.id.tv_clear_search_history:
                keyList = AppConfig.getInstance().getList("keyList");
                keyList.clear();
                Log.d("---keyList---", keyList.toString());
                AppConfig.getInstance().setList("keyList", keyList);
                mSearchHistoryTfl.setAdapter(new TagAdapter<String>(keyList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, mSearchHistoryTfl, false);
                        tv.setText(s);
                        return tv;
                    }
                });
                break;
        }
    }

    @Override
    public void onLoadSearchListSuccess(SearchBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            if (bean.getData().getDatas() != null) {
                mDatas = bean.getData().getDatas();
                if (mDatas != null) {
                    mAdapter = new SearchListAdapter(mDatas, this);
                    mAdapter.setOnItemClickListener(this);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.bringToFront();
                }
            } else {
                Toast.makeText(this, "什么也没搜到~~", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLoadSearchListError() {
        Toast.makeText(this, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSearchListSuccess(SearchBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mDatas.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadHotKeySuccess(HotKeyBean bean) {
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            //将结果显示到搜索热词的FlowLayout上
            List<String> hotKeyList = new ArrayList<>();
            for (HotKeyBean.DataBean data : bean.getData()) {
                hotKeyList.add(data.getName());
            }
            mHotKeyTfl.setAdapter(new TagAdapter<String>(hotKeyList) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, mSearchHistoryTfl, false);
                    tv.setText(s);
                    return tv;
                }
            });
        }
    }

    @Override
    public void onLoadHotKeyError() {
        Toast.makeText(this, "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        String key = mSearchEt.getText().toString();
        mPresenter.loadSearchList(mPage, key);
    }

    @Override
    public void onItemClick(View view, int position, List<SearchBean.DataBean.DatasBean> datas) {
        SearchBean.DataBean.DatasBean bean = datas.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("url", bean.getLink());
        String title;
        if (Build.VERSION.SDK_INT >= 24) {
            title = String.valueOf(Html.fromHtml(bean.getTitle(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            title = String.valueOf(Html.fromHtml(bean.getTitle()));
        }
        bundle.putString("title", title);
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
