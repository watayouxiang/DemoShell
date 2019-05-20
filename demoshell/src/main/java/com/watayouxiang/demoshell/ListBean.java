package com.watayouxiang.demoshell;

import android.view.View;

class ListBean {
    CharSequence name;
    View.OnClickListener listener;

    ListBean(CharSequence name, View.OnClickListener listener) {
        this.name = name;
        this.listener = listener;
    }
}

