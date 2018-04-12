package com.uestc.lcy.androidbook.modules;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.modules.home.HomeFragment;
import com.uestc.lcy.androidbook.modules.mine.MineFragment;
import com.uestc.lcy.androidbook.modules.sort.SortFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton rbHome, rbSort,rbMy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();
        setListener();
    }


    //默认情况下显示首页内容
    private void initFragment() {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.ll_main_content, new HomeFragment());
        transaction.commit();
    }

    private void initView() {
        rbHome = findViewById(R.id.rb_main_home);
        rbSort = findViewById(R.id.rb_main_sort);
        rbMy = findViewById(R.id.rb_main_mine);
    }

    private void setListener() {
        rbHome.setOnClickListener(this);
        rbSort.setOnClickListener(this);
        rbMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transaction = manager.beginTransaction();
        TextView topBarTitle = findViewById(R.id.tv_top_bar_title);
        switch (view.getId()) {
            case R.id.rb_main_home:
                transaction.replace(R.id.ll_main_content, new HomeFragment());
                topBarTitle.setText(getString(R.string.main_home));
                break;
            case R.id.rb_main_sort:
                transaction.replace(R.id.ll_main_content, new SortFragment());
                topBarTitle.setText(getString(R.string.main_sort));
                break;
            case R.id.rb_main_mine:
                transaction.replace(R.id.ll_main_content, new MineFragment());
                topBarTitle.setText(getString(R.string.main_my));
                break;
        }
        transaction.commit();
    }
}
