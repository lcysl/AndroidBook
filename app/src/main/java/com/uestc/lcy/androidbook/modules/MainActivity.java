package com.uestc.lcy.androidbook.modules;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.modules.home.HomeFragment;
import com.uestc.lcy.androidbook.modules.mine.MineFragment;
import com.uestc.lcy.androidbook.modules.sort.SortFragment;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private FragmentManager mFragmentManager;
    private RadioButton rbHome, rbSort,rbMy;
    private Fragment[] mFragments;
    private Fragment mContent;
    private int currentFragmentIndex = 0;

    /**
     * 获取子类的布局
     * @return
     */
    @Override
    protected int getSubLayout() {
        return R.layout.activity_main;
    }

    /**
     * 初始化操作
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        initView();
        initData();
        setListener();
        restoreLastFragment(savedInstanceState);
    }

    @Override
    protected void initPresenter() {

    }

    /**
     * 当系统回收时将当前界面下标保存
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentFragmentIndex", currentFragmentIndex);
        super.onSaveInstanceState(outState);
    }

    /**
     * 解决因内存重启导致的重叠问题
     * 恢复上次离开时的Fragment
     * @param savedInstanceState
     */
    private void restoreLastFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            //savedInstanceState不为null表明系统已回收。
            //1.获取下标
            currentFragmentIndex = savedInstanceState.getInt("currentFragmentIndex");
            //2.获取所有Fragment
            List<Fragment> fragmentList = mFragmentManager.getFragments();
            //3.将Fragment全部隐藏
            for (Fragment fragment:fragmentList) {
                mFragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
            }
            //4.显示对应下标的Fragment
            mFragmentManager.beginTransaction()
                    .show(mFragments[currentFragmentIndex])
                    .commitAllowingStateLoss();
            //5.设置mContent为当前Fragment
            mContent = mFragments[currentFragmentIndex];
        } else {
            //代码模拟按钮的点击事件
            rbHome.performClick();
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        rbHome = findViewById(R.id.rb_main_home);
        rbSort = findViewById(R.id.rb_main_sort);
        rbMy = findViewById(R.id.rb_main_mine);
    }

    /**
     * 初始化mFragments的值
     */
    private void initData() {
        mFragments = new Fragment[3];
        mFragments[0] = mFragmentManager.findFragmentByTag(HomeFragment.class.getName());
        mFragments[1] = mFragmentManager.findFragmentByTag(SortFragment.class.getName());
        mFragments[2] = mFragmentManager.findFragmentByTag(MineFragment.class.getName());
        if (mFragments[0] == null) {
            mFragments[0] = new HomeFragment();
        }
        if (mFragments[1] == null) {
            mFragments[1] = new SortFragment();
        }
        if (mFragments[2] == null) {
            mFragments[2] = new MineFragment();
        }
    }

    private void setListener() {
        rbHome.setOnClickListener(this);
        rbSort.setOnClickListener(this);
        rbMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView topBarTitle = findViewById(R.id.tv_top_bar_title);
        switch (view.getId()) {
            case R.id.rb_main_home:
                switchFragment(mFragments[currentFragmentIndex], mFragments[0]);
                topBarTitle.setText(getString(R.string.app_name));
                currentFragmentIndex = 0;
                break;
            case R.id.rb_main_sort:
                switchFragment(mFragments[currentFragmentIndex], mFragments[1]);
                topBarTitle.setText(getString(R.string.main_sort));
                currentFragmentIndex = 1;
                break;
            case R.id.rb_main_mine:
                switchFragment(mFragments[currentFragmentIndex], mFragments[2]);
                topBarTitle.setText(getString(R.string.main_my));
                currentFragmentIndex = 2;
                break;
            default:
                break;
        }
    }

    /**
     * 切换Fragment
     * @param from：从哪个fragment切换
     * @param to：切换到目标fragment
     */
    private void switchFragment(Fragment from, Fragment to) {

        android.support.v4.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        if (mContent == null) {
            mContent = to;
            mFragmentTransaction.add(R.id.ll_main_content, to, to.getClass().getName()).commitAllowingStateLoss();
        } else if (mContent != to) {//防止重复点击时再次加载
            mContent = to;
            //先判断是否被add过
            if (!to.isAdded()) {
                // 隐藏当前的fragment，add下一个到Activity中
                mFragmentTransaction.hide(from).add(R.id.ll_main_content, to, to.getClass().getName()).commit();
            } else {
                // 隐藏当前的fragment，显示下一个
                mFragmentTransaction.hide(from).show(to).commit();
            }
        }
    }
}
