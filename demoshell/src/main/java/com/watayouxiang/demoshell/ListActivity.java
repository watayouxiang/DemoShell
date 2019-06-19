package com.watayouxiang.demoshell;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ListActivity extends BaseActivity {
    @Override
    protected int getRootViewId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //初始化列表
        RecyclerView rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        ListAdapter listAdapter = new ListAdapter();
        listAdapter.setNewData(getListData());
        rv_list.setAdapter(listAdapter);
    }

    /**
     * 初始化列表数据
     *
     * @return 列表数据
     */
    protected abstract ListData getListData();
}