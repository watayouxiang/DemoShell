package com.watayouxiang.demoshell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class DemoActivity extends BaseActivity {
    @Override
    protected int getRootViewId() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //初始化占位视图
        LinearLayout ll_holder = findViewById(R.id.ll_holder);
        int holderViewId = getHolderViewId();
        if (holderViewId != 0) {
            LayoutInflater.from(this).inflate(holderViewId, ll_holder, true);
        } else {
            ll_holder.setVisibility(View.GONE);
        }
        //初始化列表
        RecyclerView rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        ListAdapter listAdapter = new ListAdapter();
        listAdapter.setNewData(getListData());
        rv_list.setAdapter(listAdapter);
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