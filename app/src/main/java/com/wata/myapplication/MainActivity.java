package com.wata.myapplication;

import android.view.View;
import android.widget.Toast;

import com.watayouxiang.demoshell.ListActivity;
import com.watayouxiang.demoshell.ListData;

public class MainActivity extends ListActivity {
    @Override
    protected boolean showBackBtn() {
        return false;
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addSection("这是标题")
                .addClick("say hi", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "hi~", Toast.LENGTH_SHORT).show();
                    }
                })
                .addWeb(this, "打开百度", "https://www.baidu.com")
                .addActivity(this, "打开Activity", HiActivity.class);
    }
}
