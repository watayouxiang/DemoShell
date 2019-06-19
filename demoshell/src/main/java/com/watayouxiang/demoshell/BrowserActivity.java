package com.watayouxiang.demoshell;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.watayouxiang.demoshell.webview.TListener;
import com.watayouxiang.demoshell.webview.TWebView;

public class BrowserActivity extends BaseActivity {
    private TWebView mTWebView;
    private ProgressBar mProgressBar;

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
        super.initView(savedInstanceState);
        mProgressBar = findViewById(R.id.progressBar);
        mTWebView = findViewById(R.id.browser);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //设置字体默认缩放大小为50%
        mTWebView.getSettings().setTextZoom(50);
        //设置监听
        setWebListener(mTWebView, mProgressBar);
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
        if (item.getItemId() == android.R.id.home) {
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
        if (mProgressBar != null) {
            mProgressBar = null;
        }
    }
}