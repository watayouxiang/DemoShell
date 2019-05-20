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
        //rootView
        setContentView(getRootViewId());
        //title
        CharSequence pageTitle = getPageTitle();
        if (pageTitle != null) {
            setTitle(pageTitle);
        }
        //back btn
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showBackBtn());
        }
        //init
        initView(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract @LayoutRes
    int getRootViewId();

    protected abstract CharSequence getPageTitle();

    protected boolean showBackBtn() {
        return true;
    }

    protected abstract void initView(Bundle savedInstanceState);

}
