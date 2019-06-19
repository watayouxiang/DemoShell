package com.watayouxiang.demoshell;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ListActivity extends BaseActivity {
    private RecyclerView rv_list;

    @Override
    protected int getRootViewId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rv_list = findViewById(R.id.rv_list);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //初始化列表
        rv_list.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        rv_list.setAdapter(new ListAdapter(getListData()));
    }

    /**
     * 初始化列表数据
     *
     * @return 列表数据
     */
    protected abstract ListData getListData();
}