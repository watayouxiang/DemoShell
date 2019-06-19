package com.watayouxiang.demoshell;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.watayouxiang.demoshell.webview.TWebView;
import com.watayouxiang.demoshell.webview.TListener;

public class BrowserActivity extends BaseActivity {
    private TWebView mTWebView;

    @Override
    protected int getRootViewId() {
        return R.layout.activity_browser;
    }

    @Override
    protected CharSequence getPageTitle() {
        return "加载中...";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        mTWebView = findViewById(R.id.browser);
        //设置字体默认缩放大小为50%
        mTWebView.getSettings().setTextZoom(50);
        //设置监听
        setWebListener(mTWebView, progressBar);
        //加载网页
        String url = getIntent().getStringExtra("URL");
        if (url != null) {
            mTWebView.loadUrl(url);
        }
    }

    private void setWebListener(TWebView webBrowser, final ProgressBar progressBar) {
        webBrowser.setListener(new TListener() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mTWebView.canGoBack()) {
                    mTWebView.goBack();
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTWebView != null) {
            mTWebView.releaseRes();
            mTWebView = null;
        }
    }
}
