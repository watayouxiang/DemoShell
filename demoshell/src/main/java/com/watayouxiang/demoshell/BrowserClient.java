package com.watayouxiang.demoshell;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

class BrowserClient extends WebViewClient {

    private List<BrowserListener> mListeners;

    public BrowserClient(List<BrowserListener> listener) {
        this.mListeners = listener;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        for (BrowserListener listener : mListeners) {
            listener.onPageStarted(url);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        for (BrowserListener listener : mListeners) {
            listener.onPageFinished(url, view.canGoBack());
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();//忽略SSL证书错误，继续加载页面
    }

}
