package com.watayouxiang.demoshell;

import android.app.Activity;
import android.util.DisplayMetrics;

public class UIUtils {

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

}
