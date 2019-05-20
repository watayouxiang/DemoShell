package com.watayouxiang.demoshell;

interface BrowserListener {

    void onPageStarted(String url);

    void onPageFinished(String url, boolean canGoBack);

    void onReceivedTitle(String title);

    void onProgressChanged(int progress);

}
