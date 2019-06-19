package com.watayouxiang.demoshell.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class TWebView extends WebView {
    private List<TListener> mListeners = new ArrayList<>();

    public TWebView(Context context) {
        this(context, null);
    }

    public TWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置默认WebSettings
        setDefaultWebSettings();
        //处理Javascript的对话框、网站图标、网站title、加载进度等
        setWebChromeClient(new TWebChromeClient(mListeners));
        //处理各种通知、请求事件
        setWebViewClient(new TWebViewClient(mListeners));
        //需要添加如下代码，否则点击H5输入框，无法弹出键盘
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setDefaultWebSettings() {
        WebSettings ws = getSettings();
        /** js交互 */
        // 告诉WebView启用JavaScript执行。默认的是false。
        ws.setJavaScriptEnabled(true);
        /** 样式 */
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);
        // 设置此属性，可任意比例缩放。
        ws.setUseWideViewPort(true);
        // 排版适应屏幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // 不缩放
        setInitialScale(100);
        // 设置字体默认缩放大小 (改变网页字体大小,setTextSize api14被弃用)
        ws.setTextZoom(100);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        /** 缓存 */
        // 启动应用缓存
        ws.setAppCacheEnabled(true);
        // 设置缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        /** 数据 */
        // 保存表单数据
        ws.setSaveFormData(true);
        // 使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        // 启用数据库
        ws.setDatabaseEnabled(true);
        // 设置可以访问文件
        ws.setAllowFileAccess(true);
        /** 其他设置 */
        // 自动加载图片
        ws.setLoadsImagesAutomatically(true);
        // WebView从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    /**
     * 设置监听
     *
     * @param listener 监听器
     */
    public void setListener(TListener listener) {
        if (listener != null && !mListeners.contains(listener)) {
            mListeners.add(listener);
        }
    }

    /**
     * 释放资源
     */
    public void releaseRes() {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
        }
        removeAllViews();
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        stopLoading();
        setWebChromeClient(null);
        setWebViewClient(null);
        destroy();
        if (mListeners != null) {
            mListeners.clear();
            mListeners = null;
        }
    }
}
