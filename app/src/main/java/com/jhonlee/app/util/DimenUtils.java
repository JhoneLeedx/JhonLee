package com.jhonlee.app.util;

import android.content.Context;

/**
 * Created by JhoneLee on 2017/2/7.
 */

public class DimenUtils {

    public static int dp2px(Context context, float dpValue) {
        return (int)(dpValue * context.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static int px2dp(Context context, float pxValue) {
        return (int)(pxValue / context.getResources().getDisplayMetrics().density + 0.5F);
    }
}

