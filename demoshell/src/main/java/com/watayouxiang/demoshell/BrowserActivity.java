package com.watayouxiang.demoshell;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class BrowserActivity extends BaseActivity {

    private Browser mBrowser;

    @Override
    protected int getRootViewId() {
        return R.layout.base_web_activity;
    }

    @Override
    protected CharSequence getPageTitle() {
        return "加载中...";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        mBrowser = findViewById(R.id.browser);
        //设置字体默认缩放大小为50%
        mBrowser.getSettings().setTextZoom(50);
        //设置监听
        setWebListener(mBrowser, progressBar);
        //加载网页
        String url = getIntent().getStringExtra("URL");
        if (url != null) {
            mBrowser.loadUrl(url);
        }
    }

    private void setWebListener(Browser webBrowser, final ProgressBar progressBar) {
        webBrowser.setListener(new BrowserListener() {
            @Override
            public void onPageStarted(String url) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(String url, boolean canGoBack) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedTitle(String title) {
                setTitle(title);
            }

            @Override
            public void onProgressChanged(int progress) {
                progressBar.setProgress(progress);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mBrowser.canGoBack()) {
                    mBrowser.goBack();
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (mBrowser != null) {
            mBrowser.releaseRes();
            mBrowser = null;
        }
        super.onDestroy();
    }
}
