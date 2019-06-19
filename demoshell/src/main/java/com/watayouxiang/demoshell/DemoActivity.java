package com.watayouxiang.demoshell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class DemoActivity extends BaseActivity {
    private RecyclerView rv_list;

    @Override
    protected int getRootViewId() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rv_list = findViewById(R.id.rv_list);
        //填充占位视图
        LinearLayout ll_holder = findViewById(R.id.ll_holder);
        LayoutInflater.from(this).inflate(getHolderViewId(), ll_holder, true);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //初始化列表
        rv_list.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        rv_list.setAdapter(new ListAdapter(getListData()));
    }

    /**
     * 获取占位视图id
     *
     * @return 占位视图id
     */
    protected abstract @LayoutRes
    int getHolderViewId();

    /**
     * 初始化列表数据
     *
     * @return 列表数据
     */
    protected abstract ListData getListData();
}