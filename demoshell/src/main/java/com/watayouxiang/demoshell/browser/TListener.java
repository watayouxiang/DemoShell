package com.watayouxiang.demoshell.browser;

import android.graphics.Bitmap;
import android.webkit.WebView;

public interface TListener {

    void onPageStarted(WebView view, String url, Bitmap favicon);

    void onPageFinished(WebView view, String url);

    void onReceivedTitle(WebView view, String title);

    void onProgressChanged(WebView view, int newProgress);

}
