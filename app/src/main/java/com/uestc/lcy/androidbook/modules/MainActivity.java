package com.uestc.lcy.androidbook.modules;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.modules.collection.CollectFragment;
import com.uestc.lcy.androidbook.modules.home.HomeFragment;
import com.uestc.lcy.androidbook.modules.my.MyFragment;
import com.uestc.lcy.androidbook.modules.sort.SortFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton rbHome, rbSort, rbCollect, rbMy;

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
        rbCollect = findViewById(R.id.rb_main_collect);
        rbMy = findViewById(R.id.rb_main_my);
    }

    private void setListener() {
        rbHome.setOnClickListener(this);
        rbSort.setOnClickListener(this);
        rbCollect.setOnClickListener(this);
        rbMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.rb_main_home:
                transaction.replace(R.id.ll_main_content, new HomeFragment());
                break;
            case R.id.rb_main_sort:
                transaction.replace(R.id.ll_main_content, new SortFragment());
                break;
            case R.id.rb_main_collect:
                transaction.replace(R.id.ll_main_content, new CollectFragment());
                break;
            case R.id.rb_main_my:
                transaction.replace(R.id.ll_main_content, new MyFragment());
                break;
        }
        transaction.commit();
    }
}
