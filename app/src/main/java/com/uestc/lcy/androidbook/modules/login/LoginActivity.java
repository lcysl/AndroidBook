package com.uestc.lcy.androidbook.modules.login;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.base.BaseActivity;
import com.uestc.lcy.androidbook.modules.register.RegisterActivity;

/**
 * Created by lcy on 2018\4\12 0012.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private TextView mToRegister;

    @Override
    protected int getSubLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        initView();
        initListener();
    }


    private void initView() {
        mToRegister = findViewById(R.id.tv_to_register);
    }

    private void initListener() {
        mToRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_to_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }
}
