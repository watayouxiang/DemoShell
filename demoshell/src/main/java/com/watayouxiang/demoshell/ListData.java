package com.watayouxiang.demoshell;

import android.content.ContextWrapper;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

public class ListData extends ArrayList<ListBean> {

    // ============================================================================
    // 标题
    // ============================================================================

    /**
     * 添加 标题item
     *
     * @param sectionName 标题名
     * @return 标题item
     */
    public ListData addSection(CharSequence sectionName) {
        return addItem(sectionName, null);
    }

    // ============================================================================
    // 点击事件
    // ============================================================================

    /**
     * 添加 点击item
     *
     * @param listener 点击事件
     * @return 点击item
     */
    public ListData addClick(View.OnClickListener listener) {
        return addClick(listener.getClass().getSimpleName(), listener);
    }

    /**
     * 添加 点击item
     *
     * @param name     item名字
     * @param listener 点击事件
     * @return 点击item
     */
    public ListData addClick(String name, View.OnClickListener listener) {
        String TAG_CLICK = " [点击事件] ";
        return addItem(TAG_CLICK + name, listener);
    }

    // ============================================================================
    // 打开Activity
    // ============================================================================

    /**
     * 添加 ActivityItem
     *
     * @param context 上下文
     * @param cls     Activity字节码
     * @return ActivityItem
     */
    public ListData addActivity(final ContextWrapper context, final Class<?> cls) {
        return addActivity(context, cls.getSimpleName(), cls);
    }

    /**
     * 添加 ActivityItem
     *
     * @param context 上下文
     * @param name    item名字
     * @param cls     Activity字节码
     * @return ActivityItem
     */
    public ListData addActivity(final ContextWrapper context, CharSequence name, final Class<?> cls) {
        String TAG_PAGE = " [跳转页面] ";
        return addItem(TAG_PAGE + name, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, cls));
            }
        });
    }

    // ============================================================================
    // 打开网页
    // ============================================================================

    /**
     * 添加 网页item
     *
     * @param context 上下文
     * @param url     网页地址
     * @return 网页item
     */
    public ListData addWeb(final ContextWrapper context, final String url) {
        return addWeb(context, getLastUrlTxt(url), url);
    }

    /**
     * 添加 网页item
     *
     * @param context 上下文
     * @param name    item名字
     * @param url     网页地址
     * @return 网页item
     */
    public ListData addWeb(final ContextWrapper context, CharSequence name, final String url) {
        String TAG_WEB = " [打开链接] ";
        return addItem(TAG_WEB + name, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BrowserActivity.class);
                intent.putExtra("URL", url);
                context.startActivity(intent);
            }
        });
    }

    //==============================================================================================

    private ListData addItem(CharSequence name, View.OnClickListener listener) {
        this.add(new ListBean(name, listener));
        return this;
    }

    private CharSequence getLastUrlTxt(String url) {
        String name = null;
        int index = url.lastIndexOf("/");
        if (index != -1) {
            name = url.substring(index + 1);
        }
        return name;
    }
}
