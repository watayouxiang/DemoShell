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
        //设置根布局
        setContentView(getRootViewId());
        //设置标题
        CharSequence pageTitle = getPageTitle();
        setTitle(pageTitle != null ? pageTitle : getClass().getSimpleName());
        //返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showBackBtn());
        }
        //初始化其他布局
        initView(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
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
     * @return 标题
     */
    protected CharSequence getPageTitle() {
        return null;
    }

    /**
     * 是否显示返回按钮
     *
     * @return 默认显示
     */
    protected boolean showBackBtn() {
        return true;
    }

    /**
     * 初始化页面
     *
     * @param savedInstanceState bundle
     */
    protected void initView(Bundle savedInstanceState) {
    }
}