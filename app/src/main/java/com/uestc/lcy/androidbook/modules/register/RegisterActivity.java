package com.uestc.lcy.androidbook.modules.register;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;

/**
 * Created by lcy on 2018\4\13 0013.
 */

public class RegisterActivity extends BaseActivity {

    private LinearLayout mRegisterTopBarLayout;
    private TextView mRegisterTopBarTitle;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mRegisterTopBarLayout = findViewById(R.id.top_bar_register);
        mRegisterTopBarTitle = mRegisterTopBarLayout.findViewById(R.id.tv_top_bar2_title);

        mRegisterTopBarTitle.setText(R.string.register);
    }
}
