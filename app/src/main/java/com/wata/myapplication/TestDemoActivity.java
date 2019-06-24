package com.wata.myapplication;

import android.view.View;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;

public class TestDemoActivity extends DemoActivity {
    @Override
    protected int getHolderViewId() {
        return R.layout.my_own_view;
    }

    @Override
    protected ListData getListData() {
        return new ListData().addClick("TestClick", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
