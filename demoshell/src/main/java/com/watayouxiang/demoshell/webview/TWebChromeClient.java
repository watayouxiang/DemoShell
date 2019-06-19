package com.watayouxiang.demoshell.webview;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

class TWebChromeClient extends WebChromeClient {
    private List<TListener> mListeners;

    TWebChromeClient(List<TListener> listener) {
        this.mListeners = listener;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        for (TListener listener : mListeners) {
            listener.onReceivedTitle(view, title);
        }
    }

    @Override
    public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
        //由于webView不支持js的alert弹窗，所以使用dialog弹窗替代
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(webView.getContext());
        dialogBuilder.setMessage(message).setPositiveButton("确定", null);
        dialogBuilder.setCancelable(true);
        dialogBuilder.create().show();
        //表示处理结果为确定状态，同时唤醒WebCore线程，否则不能继续点击按钮
        result.confirm();
        return true;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress >= 0 && newProgress <= 100) {
            for (TListener listener : mListeners) {
                listener.onProgressChanged(view, newProgress);
            }
        }
    }
}
