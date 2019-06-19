package com.watayouxiang.demoshell;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    /**
     * 初始化页面
     *
     * @param savedInstanceState bundle
     */
    protected void initView(Bundle savedInstanceState) {
        //设置根布局
        setContentView(getRootViewId());
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState bundle
     */
    protected void initData(Bundle savedInstanceState) {
        //显隐返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showBackBtn());
        }
        //设置标题
        setTitle(getPageTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (showBackBtn() && item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取根布局id
     *
     * @return 根布局id
     */
    protected abstract @LayoutRes
    int getRootViewId();

    /**
     * 设置标题
     *
     * @return 默认类名
     */
    protected CharSequence getPageTitle() {
        return getClass().getSimpleName();
    }

    /**
     * 是否显示返回按钮
     *
     * @return 默认显示
     */
    protected boolean showBackBtn() {
        return true;
    }
}