package com.uestc.lcy.androidbook.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.uestc.lcy.androidbook.R;

/**
 * Created by Administrator on 2018\4\12 0012.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T mPresenter;
    private LinearLayout mBaseLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //获取父布局
        mBaseLayout = findViewById(R.id.layout_base);
        initView();
        init(savedInstanceState);
        initPresenter();
    }

    //获取子类的布局，抽象方法，由子类实现
    protected abstract int getSubLayout();
    //抽象方法，由子类实现，完成子类的一些初始化操作
    protected abstract void init(Bundle savedInstanceState);
    //初始化子类的presenter
    protected abstract void initPresenter();

    /**
     * baseactivity的初始化操作
     */
    private void initView() {
        View view = LayoutInflater.from(this).inflate(getSubLayout(), null);
        mBaseLayout.addView(view);
    }


}
