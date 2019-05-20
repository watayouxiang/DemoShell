package com.watayouxiang.demoshell;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView rv_list = findViewById(R.id.rv_list);
        initList(rv_list);
    }

    @Override
    protected int getRootViewId() {
        return R.layout.base_list_activity;
    }

    private void initList(RecyclerView rv_list) {
        rv_list.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        ListAdapter listAdapter = new ListAdapter(getListData());
        rv_list.setAdapter(listAdapter);
    }

    protected abstract ListData getListData();
}
