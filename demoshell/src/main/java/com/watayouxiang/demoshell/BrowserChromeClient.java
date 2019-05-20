package com.watayouxiang.demoshell;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

class BrowserChromeClient extends WebChromeClient {

    private List<BrowserListener> mListeners;

    BrowserChromeClient(List<BrowserListener> listener) {
        this.mListeners = listener;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (title != null) {
            for (BrowserListener listener : mListeners) {
                listener.onReceivedTitle(title);
            }
        }
    }

    //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
    @Override
    public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(webView.getContext());
        dialogBuilder.setMessage(message).setPositiveButton("确定", null);
        dialogBuilder.setCancelable(true);
        dialogBuilder.create().show();

        //注意:
        //必须要这一句代码:result.confirm()表示:
        //处理结果为确定状态同时唤醒WebCore线程
        //否则不能继续点击按钮
        result.confirm();
        return true;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress >= 0 && newProgress <= 100) {
            for (BrowserListener listener : mListeners) {
                listener.onProgressChanged(newProgress);
            }
        }
    }
}
