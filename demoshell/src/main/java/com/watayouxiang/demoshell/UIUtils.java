package com.watayouxiang.demoshell;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

class UIUtils {
    static void setPadding(View view, int padding) {
        setPadding(view, new Rect(padding, padding, padding, padding));
    }

    static void setPadding(View view, Rect padding) {
        int paddingLeft = dp2px(view.getContext(), padding.left);
        int paddingTop = dp2px(view.getContext(), padding.top);
        int paddingRight = dp2px(view.getContext(), padding.right);
        int paddingBottom = dp2px(view.getContext(), padding.bottom);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    static void setMargin(View view, int margin) {
        setMargin(view, new Rect(margin, margin, margin, margin));
    }

    static void setMargin(View view, Rect margin) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) lp;
            int marginLeft = dp2px(view.getContext(), margin.left);
            int marginTop = dp2px(view.getContext(), margin.top);
            int marginRight = dp2px(view.getContext(), margin.right);
            int marginBottom = dp2px(view.getContext(), margin.bottom);
            params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        }
    }

    static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
