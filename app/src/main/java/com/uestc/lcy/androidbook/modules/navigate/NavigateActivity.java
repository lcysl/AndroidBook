package com.uestc.lcy.androidbook.modules.navigate;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.model.NavigateBean;
import com.uestc.lcy.androidbook.modules.navigate.adapter.NavigateAdapter;
import com.uestc.lcy.androidbook.modules.navigate.presenter.NavigatePresenter;
import com.uestc.lcy.androidbook.modules.navigate.view.NavigateView;

import java.util.List;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public class NavigateActivity extends BaseActivity<NavigatePresenter> implements NavigateView{

    /*标题栏*/
    private Button mBackBtn;
    private TextView mTopBarTitle;
    /*RecyclerView相关*/
    private RecyclerView mRecyclerView;
    private NavigateAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    /*列表项的内容*/
    private List<NavigateBean.DataBean> mData;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_navigate;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        setListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new NavigatePresenter();
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
        mTopBarTitle.setText("导航");

        mRecyclerView = findViewById(R.id.rv_navigate);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initData() {
        mPresenter.loadNavigation();
    }

    private void setListener() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 网络请求成功后更新列表项
     * @param bean
     */
    @Override
    public void onLoadNavigationSuccess(NavigateBean bean) {
//        Toast.makeText(this, "网络请求成功", Toast.LENGTH_SHORT).show();
        if (bean.getErrorCode() == -1 && bean.getErrorMsg() != null) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else if (bean.getErrorCode() == 0 && bean.getData() != null){
            mData = bean.getData();
            mAdapter = new NavigateAdapter(mData, this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoadNavigationError() {
        Toast.makeText(this, "网络请求失败", Toast.LENGTH_SHORT).show();
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
