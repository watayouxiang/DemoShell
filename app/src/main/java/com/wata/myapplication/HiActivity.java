package com.wata.myapplication;

import android.os.Bundle;

import com.watayouxiang.demoshell.BaseActivity;

public class HiActivity extends BaseActivity {
    @Override
    protected int getRootViewId() {
        return R.layout.activity_hi;
    }

    @Override
    protected CharSequence getPageTitle() {
        return "新的页面";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
