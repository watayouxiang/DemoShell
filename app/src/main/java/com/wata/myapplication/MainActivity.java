package com.wata.myapplication;

import android.view.View;
import android.widget.Toast;

import com.watayouxiang.demoshell.ListActivity;
import com.watayouxiang.demoshell.ListData;

public class MainActivity extends ListActivity {
    @Override
    protected ListData getListData() {
        return new ListData()
                .addSection("这是标题")
                .addClick(new HiClick())
                .addWeb(this, "https://www.baidu.com")
                .addActivity(this, "Android知识点", HiActivity.class);
    }

    class HiClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "hi~", Toast.LENGTH_SHORT).show();
        }
    }
}
