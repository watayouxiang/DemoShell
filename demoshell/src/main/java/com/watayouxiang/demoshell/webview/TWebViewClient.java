package com.watayouxiang.demoshell.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

class TWebViewClient extends WebViewClient {
    private List<TListener> mListeners;

    TWebViewClient(List<TListener> listener) {
        this.mListeners = listener;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        for (TListener listener : mListeners) {
            listener.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        for (TListener listener : mListeners) {
            listener.onPageFinished(view, url);
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();//忽略SSL证书错误，继续加载页面
    }

}
